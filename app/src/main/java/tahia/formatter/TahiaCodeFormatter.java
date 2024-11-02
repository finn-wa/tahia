package tahia.formatter;

import jakarta.annotation.Nullable;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/** Formats files and tracks stats */
public class TahiaCodeFormatter {
    private static final Logger LOGGER = Logger.getLogger(TahiaCodeFormatter.class.getName());

    private final DefaultCodeFormatter jdtFormatter;
    private int numFilesFormatted = 0;
    private List<Path> skippedFiles = new ArrayList<>();

    public TahiaCodeFormatter(Map<String, String> formatterConfig) {
        this.jdtFormatter = new DefaultCodeFormatter(formatterConfig);
    }

    public TahiaCodeFormatter(DefaultCodeFormatter jdtFormatter) {
        this.jdtFormatter = jdtFormatter;
    }

    /**
     * Format the given Java source file.
     */
    public void formatFile(Path path) {
        try {
            final String contents = Files.readString(path);
            final var fileType = FileType.fromFileName(path.getFileName().toString());
            final String formatted = formatContent(contents, fileType.kind);
            if (formatted != null) {
                Files.writeString(path, formatted);
                numFilesFormatted++;
                return;
            }
            LOGGER.warning("Unable to format " + path.toString() + " - skipping file");
        } catch (IOException | MalformedTreeException | BadLocationException e) {
            LOGGER.warning(
                () -> "Caught " + e.getClass().getName() + " while formatting " + path.toString() + ": " + e
                    .getLocalizedMessage()
            );
        }
        skippedFiles.add(path);
    }

    private @Nullable String formatContent(String source, int kind)
        throws MalformedTreeException, BadLocationException {
        final TextEdit edit = jdtFormatter.format(
            kind,
            source,
            new IRegion[] { new Region(0, source.length()) },
            0,
            null
        );
        final var doc = new EditableDocumentStub(source);
        edit.apply(doc, TextEdit.NONE);
        return doc.get();
    }

    public int getNumFilesFormatted() {
        return numFilesFormatted;
    }

    public List<Path> getSkippedFiles() {
        return Collections.unmodifiableList(skippedFiles);
    }
}
