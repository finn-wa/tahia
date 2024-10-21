package tahia.formatter;

import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import tahia.formatter.EntireFileCodeFormatter.FileType;

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

    private final EntireFileCodeFormatter formatter;
    private int numFilesFormatted = 0;
    private List<Path> skippedFiles = new ArrayList<>();

    public TahiaCodeFormatter(Map<String, String> formatterConfig) {
        final var options = new DefaultCodeFormatterOptions(formatterConfig);
        this.formatter = new EntireFileCodeFormatter(options);
    }

    public TahiaCodeFormatter(EntireFileCodeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Format the given Java source file.
     */
    public void formatFile(Path path) {
        try {
            final String contents = Files.readString(path);
            final var fileType = FileType.fromFileName(path.getFileName().toString());
            final String formatted = formatter.format(contents, fileType);
            if (formatted != null) {
                Files.writeString(path, formatted);
                numFilesFormatted++;
                return;
            }
            LOGGER.warning("Unable to format " + path.toString() + " - skipping file");
        } catch (IOException e) {
            LOGGER.warning(
                () -> "Caught IOEXception while formatting " + path.toString() + ": " + e
                    .getLocalizedMessage()
            );
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
