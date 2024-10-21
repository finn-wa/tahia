package tahia.formatter;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IModuleDescription;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.lookup.TypeConstants;
import org.eclipse.jdt.internal.compiler.parser.Scanner;
import org.eclipse.jdt.internal.compiler.tool.Util;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.core.SourceModule;
import org.eclipse.jdt.internal.formatter.CommentsPreparator;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.jdt.internal.formatter.LineBreaksPreparator;
import org.eclipse.jdt.internal.formatter.OneLineEnforcer;
import org.eclipse.jdt.internal.formatter.SpacePreparator;
import org.eclipse.jdt.internal.formatter.TextEditsBuilder;
import org.eclipse.jdt.internal.formatter.Token;
import org.eclipse.jdt.internal.formatter.TokenManager;
import org.eclipse.jdt.internal.formatter.linewrap.WrapPreparator;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.TextEdit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT;
import static org.eclipse.jdt.internal.compiler.parser.TerminalTokens.TokenNameEOF;
import static org.eclipse.jdt.internal.compiler.parser.TerminalTokens.TokenNameNotAToken;

public class EntireFileCodeFormatter {
    public final boolean previewEnabled;
    private static final Logger LOGGER = Logger.getLogger(EntireFileCodeFormatter.class.getName());
    private final DefaultCodeFormatterOptions options;
    private final String sourceLevel;

    public EntireFileCodeFormatter() {
        this(DefaultCodeFormatterOptions.getDefaultSettings());
    }

    public EntireFileCodeFormatter(DefaultCodeFormatterOptions options) {
        this.options = options;
        this.sourceLevel = CompilerOptions.getLatestVersion();
        this.previewEnabled = false;
        if (this.options.line_separator == null) {
            this.options.line_separator = Util.LINE_SEPARATOR;
        }
        this.options.initial_indentation_level = 0;
    }

    public enum FileType {
        CODE(CodeFormatter.K_COMPILATION_UNIT | CodeFormatter.F_INCLUDE_COMMENTS),
        MODULE_INFO(CodeFormatter.K_MODULE_INFO | CodeFormatter.F_INCLUDE_COMMENTS);

        final int kind;

        private FileType(int kind) {
            this.kind = kind;
        }

        public static FileType fromFileName(String name) {
            return name.endsWith("module-info.java")
                ? FileType.MODULE_INFO
                : FileType.CODE;
        }
    }

    public String format(String source, FileType fileType) {
        final var edit = buildFormatEdit(source, fileType);
        final var doc = new SimpleDocument(source);
        try {
            edit.apply(doc, TextEdit.NONE);
        } catch (MalformedTreeException | BadLocationException e) {
            LOGGER.fine(
                () -> "Error thrown while formatting: %s - %s".formatted(e.getClass().getSimpleName(), e.getMessage())
            );
            return null;
        }
        return doc.get();
    }

    private MultiTextEdit buildFormatEdit(String source, FileType fileType) {
        final TokenManager tokenManager = prepareFormattedCode(source, fileType);
        if (tokenManager == null) {
            return null;
        }
        final var resultBuilder = new TextEditsBuilder(
            source,
            List.of(new Region(0, source.length())),
            tokenManager,
            this.options
        );
        tokenManager.traverse(0, resultBuilder);
        final var multiEdit = new MultiTextEdit();
        for (TextEdit edit : resultBuilder.getEdits()) {
            multiEdit.addChild(edit);
        }
        return multiEdit;
    }

    private TokenManager prepareFormattedCode(String source, FileType fileType) {
        final char[] sourceArray = source.toCharArray();
        final List<Token> tokens = tokenizeSource(sourceArray, fileType);
        if (tokens.isEmpty()) {
            return null;
        }
        final var tokenManager = new TokenManager(tokens, source, this.options);
        final var astRoot = createParser(sourceArray, fileType).createAST(null);
        if (astRoot == null)
            return null;

        setHeader(tokenManager, astRoot);
        prepareSpaces(tokenManager, astRoot);
        prepareLineBreaks(tokenManager, astRoot);
        prepareComments(tokenManager, astRoot);
        prepareWraps(tokenManager, astRoot, fileType, source.length());

        return tokenManager;
    }

    private List<Token> tokenizeSource(char[] sourceArray, FileType fileType) {
        final List<Token> tokens = new ArrayList<>();
        Scanner scanner = new Scanner(
            true,
            false,
            false/* nls */,
            CompilerOptions.versionToJdkLevel(this.sourceLevel),
            null/* taskTags */,
            null/* taskPriorities */,
            false/* taskCaseSensitive */,
            this.previewEnabled
        );
        scanner.setSource(sourceArray);
        scanner.fakeInModule = fileType == FileType.MODULE_INFO;
        while (true) {
            try {
                int tokenType = scanner.getNextToken();
                if (tokenType == TokenNameEOF)
                    break;
                Token token = Token.fromCurrent(scanner, tokenType);
                tokens.add(token);
            } catch (InvalidInputException e) {
                Token token = Token.fromCurrent(scanner, TokenNameNotAToken);
                tokens.add(token);
            }
        }
        return tokens;
    }

    private ASTParser createParser(char[] sourceArray, FileType fileType) {
        ASTParser parser = ASTParser.newParser(AST.JLS22);

        if (fileType == FileType.MODULE_INFO) {
            parser.setSource(createDummyModuleInfoCompilationUnit(sourceArray));
        } else {
            parser.setSource(sourceArray);
        }
        parser.setKind(K_COMPILATION_UNIT);

        Map<String, String> parserOptions = JavaCore.getOptions();
        parserOptions.put(CompilerOptions.OPTION_Source, this.sourceLevel);
        parserOptions.put(CompilerOptions.OPTION_DocCommentSupport, CompilerOptions.ENABLED);
        parserOptions.put(CompilerOptions.OPTION_EnablePreviews, CompilerOptions.ENABLED); // TODO
        parserOptions.put(CompilerOptions.OPTION_ReportPreviewFeatures, CompilerOptions.IGNORE);
        parser.setCompilerOptions(parserOptions);
        return parser;
    }

    private ICompilationUnit createDummyModuleInfoCompilationUnit(
        char[] sourceArray
    ) {
        JavaProject dummyProject = new JavaProject(null, null) {
            @Override
            public Map<String, String> getOptions(boolean inheritJavaCoreOptions) {
                return new HashMap<>();
            }

            @Override
            public IModuleDescription getModuleDescription() throws JavaModelException {
                return new SourceModule(this, ""); //$NON-NLS-1$
            }
        };
        return new org.eclipse.jdt.internal.core.CompilationUnit(
            null,
            TypeConstants.MODULE_INFO_FILE_NAME_STRING,
            null
        ) {
            @Override
            public char[] getContents() {
                return sourceArray;
            }

            @Override
            public JavaProject getJavaProject() {
                return dummyProject;
            }
        };
    }

    private void setHeader(TokenManager tokenManager, ASTNode astRoot) {
        if (astRoot instanceof CompilationUnit unit) {
            List<TypeDeclaration> types = unit.types();
            ASTNode firstElement = !types.isEmpty()
                ? types.get(0)
                : unit.getModule() != null ? unit.getModule()
                : unit.getPackage();
            if (firstElement != null) {
                int headerEndIndex = tokenManager.firstIndexIn(firstElement, -1);
                tokenManager.setHeaderEndIndex(headerEndIndex);
            }
        }
    }

    private void prepareSpaces(TokenManager tokenManager, ASTNode astRoot) {
        SpacePreparator spacePreparator = new SpacePreparator(tokenManager, this.options);
        astRoot.accept(spacePreparator);
        spacePreparator.finishUp();
    }

    private void prepareLineBreaks(TokenManager tokenManager, ASTNode astRoot) {
        LineBreaksPreparator breaksPreparator = new LineBreaksPreparator(tokenManager, this.options);
        astRoot.accept(breaksPreparator);
        breaksPreparator.finishUp();
        astRoot.accept(new OneLineEnforcer(tokenManager, this.options));
    }

    private void prepareComments(TokenManager tokenManager, ASTNode astRoot) {
        CommentsPreparator commentsPreparator = new CommentsPreparator(
            tokenManager,
            this.options,
            this.sourceLevel
        );
        List<Comment> comments = ((CompilationUnit) astRoot.getRoot()).getCommentList();
        for (Comment comment : comments) {
            comment.accept(commentsPreparator);
        }
        commentsPreparator.finishUp();
    }

    private void prepareWraps(TokenManager tokenManager, ASTNode astRoot, FileType fileType, int sourceLength) {
        WrapPreparator wrapPreparator = new WrapPreparator(tokenManager, this.options, fileType.kind);
        astRoot.accept(wrapPreparator);
        final List<IRegion> regions = getRegions(tokenManager, sourceLength);
        wrapPreparator.finishUp(astRoot, regions);
    }

    private List<IRegion> getRegions(TokenManager tokenManager, int sourceLength) {
        List<IRegion> regions = List.of(new Region(0, sourceLength));
        for (Token[] offPair : tokenManager.getDisableFormatTokenPairs()) {
            final int offStart = offPair[0].originalStart;
            final int offEnd = offPair[1].originalEnd;

            offPair[0].setWrapPolicy(null);
            offPair[0]
                .setIndent(Math.min(offPair[0].getIndent(), tokenManager.findSourcePositionInLine(offStart)));

            final List<IRegion> result = new ArrayList<>();
            for (IRegion region : regions) {
                final int start = region.getOffset(), end = region.getOffset() + region.getLength() - 1;
                if (offEnd < start || end < offStart) {
                    result.add(region);
                } else if (offStart <= start && end <= offEnd) {
                    // whole region off
                } else {
                    if (start < offStart)
                        result.add(new Region(start, offStart - start));
                    if (offEnd < end)
                        result.add(new Region(offEnd + 1, end - offEnd));
                }
            }
            regions = result;
        }
        return regions;
    }

}
