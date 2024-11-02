package tahia.formatter;

import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/** Formats files and tracks stats */
public class ThreadSafeCodeFormatter {
    private static final Logger LOGGER = Logger.getLogger(ThreadSafeCodeFormatter.class.getName());

    private final EntireFileCodeFormatter formatter;
    private AtomicInteger numFilesFormatted = new AtomicInteger(0);
    private List<Path> skippedFiles = new ArrayList<>();

    public ThreadSafeCodeFormatter(Map<String, String> formatterConfig) {
        final var options = new DefaultCodeFormatterOptions(formatterConfig);
        this.formatter = new EntireFileCodeFormatter(options);
    }

    public ThreadSafeCodeFormatter(EntireFileCodeFormatter jdtFormatter) {
        this.formatter = jdtFormatter;
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
                numFilesFormatted.incrementAndGet();
                return;
            }
            LOGGER.warning("Unable to format " + path.toString() + " - skipping file");
        } catch (IOException e) {
            LOGGER.warning(
                () -> "Caught " + e.getClass().getName() + " while formatting " + path.toString() + ": " + e
                    .getLocalizedMessage()
            );
        }
        addSkippedFile(path);
    }

    private synchronized void addSkippedFile(Path file) {
        skippedFiles.add(file);
    }

    public int getNumFilesFormatted() {
        return numFilesFormatted.get();
    }

    public List<Path> getSkippedFiles() {
        return Collections.unmodifiableList(skippedFiles);
    }
}
