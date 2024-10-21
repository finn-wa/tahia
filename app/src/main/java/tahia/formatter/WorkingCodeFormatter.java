package tahia.formatter;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IModuleDescription;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.compiler.IProblem;
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
import org.eclipse.jdt.internal.compiler.util.Util;
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
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.TextEdit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.eclipse.jdt.core.formatter.CodeFormatter.K_COMPILATION_UNIT;
import static org.eclipse.jdt.core.formatter.CodeFormatter.K_MODULE_INFO;
import static org.eclipse.jdt.internal.compiler.parser.TerminalTokens.TokenNameEOF;
import static org.eclipse.jdt.internal.compiler.parser.TerminalTokens.TokenNameNotAToken;

public class WorkingCodeFormatter {

    private final DefaultCodeFormatterOptions options;

    private String sourceLevel;
    public boolean previewEnabled;

    private String sourceString;
    char[] sourceArray;
    private List<IRegion> formatRegions;

    private ASTNode astRoot;
    private final List<Token> tokens = new ArrayList<>();
    private TokenManager tokenManager;

    public WorkingCodeFormatter() {
        this(DefaultCodeFormatterOptions.getDefaultSettings());
    }

    public WorkingCodeFormatter(DefaultCodeFormatterOptions options) {
        this.options = options;
        this.sourceLevel = CompilerOptions.getLatestVersion();
        this.previewEnabled = false;
        if (this.options.line_separator == null)
            this.options.line_separator = Util.LINE_SEPARATOR;

        this.options.initial_indentation_level = 0;
    }

    /**
     * @see org.eclipse.jdt.core.formatter.CodeFormatter#format(int, java.lang.String, int, int,
     *     int, java.lang.String)
     */
    public TextEdit format(
        int kind,
        String source,
        int offset,
        int length,
        int indentationLevel,
        String lineSeparator
    ) {
        return format(kind, source, new IRegion[] { new Region(offset, length) }, indentationLevel, lineSeparator);
    }

    public TextEdit format(int kind, String source, IRegion[] regions, int indentationLevel, String lineSeparator) {
        // assertRegionPreconditions(regions, source.length());
        this.formatRegions = Arrays.asList(regions);

        if (prepareFormattedCode(source, kind) == null)
            return this.tokens.isEmpty() ? new MultiTextEdit() : null;
        // this.tokens.get(0).toString()

        MultiTextEdit result = new MultiTextEdit();
        var resultBuilder = new TextEditsBuilder(
            this.sourceString,
            this.formatRegions,
            this.tokenManager,
            this.options
        );
        this.tokenManager.traverse(0, resultBuilder);
        // this.tokenManager.traverse(0,)
        final var edits = resultBuilder.getEdits();
        for (TextEdit edit : edits) {
            result.addChild(edit);
        }
        return result;
    }

    public String format(String source, int kind) {
        this.formatRegions = Arrays.asList(new IRegion[] { new Region(0, source.length()) });

        if (prepareFormattedCode(source, kind) == null)
            return this.tokens.isEmpty() ? source : null;
        // final TokenManager tokenManager = prepareFormattedCode(source, kind);
        // if (tokenManager == null) {
        // return null;
        // }
        var resultBuilder = new ExperimentalTextEditsBuilder(
            source,
            this.formatRegions,
            tokenManager,
            this.options
        );
        tokenManager.traverse(0, resultBuilder);

        final var out = new StringBuilder(source);
        for (var edit : resultBuilder.getEdits()) {
            out.replace(edit.editStart(), edit.editEnd(), edit.text());
        }
        // for (TextEdit edit : resultBuilder.getEdits()) {
        //     if (edit instanceof ReplaceEdit replaceEdit) {
        //         out.replace(
        //             replaceEdit.getOffset(),
        //             replaceEdit.getOffset() + replaceEdit.getLength(),
        //             replaceEdit.getText()
        //         );
        //     } else {
        //         throw new IllegalStateException("Edit is not a ReplaceEdit! " + edit.toString());
        //     }
        // }
        return out.toString();
    }

    List<Token> prepareFormattedCode(String source) {
        this.formatRegions = Arrays.asList(new Region(0, source.length()));
        return prepareFormattedCode(source, CodeFormatter.K_UNKNOWN);
    }

    private List<Token> prepareFormattedCode(String source, int kind) {
        this.sourceString = source;
        this.sourceArray = source.toCharArray();
        this.tokens.clear();
        this.tokenManager = new TokenManager(this.tokens, source, this.options);

        tokenizeSource(kind);

        if (this.tokens.isEmpty()) {
            return null;
        }

        this.astRoot = createParser(kind).createAST(null);
        if (this.astRoot == null)
            return null;

        if (kind != CodeFormatter.K_UNKNOWN)
            findHeader();

        prepareSpaces();
        prepareLineBreaks();
        prepareComments();
        prepareWraps(kind);

        return this.tokens;
    }

    private void findHeader() {
        if (this.astRoot instanceof CompilationUnit unit) {
            List<TypeDeclaration> types = unit.types();
            ASTNode firstElement = !types.isEmpty()
                ? types.get(0)
                : unit.getModule() != null ? unit.getModule()
                : unit.getPackage();
            if (firstElement != null) {
                int headerEndIndex = this.tokenManager.firstIndexIn(firstElement, -1);
                this.tokenManager.setHeaderEndIndex(headerEndIndex);
            }
        }
    }

    private ASTParser createParser(int kind) {
        ASTParser parser = ASTParser.newParser(AST.JLS22);

        if (kind == K_MODULE_INFO) {
            parser.setSource(createDummyModuleInfoCompilationUnit());
        } else {
            parser.setSource(this.sourceArray);
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

    private ICompilationUnit createDummyModuleInfoCompilationUnit() {
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
                return WorkingCodeFormatter.this.sourceArray;
            }

            @Override
            public JavaProject getJavaProject() {
                return dummyProject;
            }
        };
    }

    private boolean hasErrors(ASTNode astNode) {
        CompilationUnit root = (CompilationUnit) astNode.getRoot();
        for (IProblem problem : root.getProblems()) {
            if (problem.isError())
                return true;
        }
        return false;
    }

    private void tokenizeSource(int kind) {
        this.tokens.clear();
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
        scanner.setSource(this.sourceArray);
        scanner.fakeInModule = (kind & K_MODULE_INFO) != 0;
        while (true) {
            try {
                int tokenType = scanner.getNextToken();
                if (tokenType == TokenNameEOF)
                    break;
                Token token = Token.fromCurrent(scanner, tokenType);
                this.tokens.add(token);
            } catch (InvalidInputException e) {
                Token token = Token.fromCurrent(scanner, TokenNameNotAToken);
                this.tokens.add(token);
            }
        }
    }

    private void prepareSpaces() {
        SpacePreparator spacePreparator = new SpacePreparator(this.tokenManager, this.options);
        this.astRoot.accept(spacePreparator);
        spacePreparator.finishUp();
    }

    private void prepareLineBreaks() {
        LineBreaksPreparator breaksPreparator = new LineBreaksPreparator(this.tokenManager, this.options);
        this.astRoot.accept(breaksPreparator);
        breaksPreparator.finishUp();
        this.astRoot.accept(new OneLineEnforcer(this.tokenManager, this.options));
    }

    private void prepareComments() {
        CommentsPreparator commentsPreparator = new CommentsPreparator(
            this.tokenManager,
            this.options,
            this.sourceLevel
        );
        List<Comment> comments = ((CompilationUnit) this.astRoot.getRoot()).getCommentList();
        for (Comment comment : comments) {
            comment.accept(commentsPreparator);
        }
        commentsPreparator.finishUp();
    }

    private void prepareWraps(int kind) {
        WrapPreparator wrapPreparator = new WrapPreparator(this.tokenManager, this.options, kind);
        this.astRoot.accept(wrapPreparator);
        applyFormatOff();
        wrapPreparator.finishUp(this.astRoot, this.formatRegions);
    }

    private void applyFormatOff() {
        for (Token[] offPair : this.tokenManager.getDisableFormatTokenPairs()) {
            final int offStart = offPair[0].originalStart;
            final int offEnd = offPair[1].originalEnd;

            offPair[0].setWrapPolicy(null);
            offPair[0]
                .setIndent(Math.min(offPair[0].getIndent(), this.tokenManager.findSourcePositionInLine(offStart)));

            final List<IRegion> result = new ArrayList<>();
            for (IRegion region : this.formatRegions) {
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
            this.formatRegions = result;
        }
    }

}
