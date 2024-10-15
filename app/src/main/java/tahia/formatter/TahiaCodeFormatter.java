package tahia.formatter;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.compiler.env.IModule;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/** Formats files and tracks stats. Wraps the Eclipse JDT CodeFormatter. */
public class TahiaCodeFormatter {
    private static final Logger LOGGER = Logger.getLogger(TahiaCodeFormatter.class.getName());

    private final CodeFormatter formatter;
    private int numFilesFormatted = 0;
    private List<Path> skippedFiles = new ArrayList<>();

    public TahiaCodeFormatter(Map<Object, Object> formatterConfig) {
        this.formatter = ToolFactory.createCodeFormatter(formatterConfig,
                ToolFactory.M_FORMAT_EXISTING);
    }

    public TahiaCodeFormatter(CodeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Format the given Java source file.
     */
    public void formatFile(Path path) {
        final IDocument doc = new Document();
        try {
            final String contents = Files.readString(path);
            doc.set(contents);
            final int kind = path.getFileName().toString().equals(IModule.MODULE_INFO_JAVA)
                    ? CodeFormatter.K_MODULE_INFO | CodeFormatter.F_INCLUDE_COMMENTS
                    : CodeFormatter.K_COMPILATION_UNIT | CodeFormatter.F_INCLUDE_COMMENTS;
            TextEdit edit = formatter.format(kind, contents, 0, contents.length(), 0, null);
            if (edit != null) {
                edit.apply(doc, TextEdit.NONE);
                Files.writeString(path, doc.get());
                numFilesFormatted++;
                return;
            }
            LOGGER.warning("Unable to format " + path.toString() + " - skipping file");
        } catch (IOException | BadLocationException e) {
            LOGGER.warning("Caught " + e.getClass().getSimpleName() +
                    " while formatting " + path.toString() + ": " + e.getLocalizedMessage());
        }
        skippedFiles.add(path);
    }

    public int getNumFilesFormatted() {
        return numFilesFormatted;
    }

    public List<Path> getSkippedFiles() {
        return Collections.unmodifiableList(skippedFiles);
    }
}
