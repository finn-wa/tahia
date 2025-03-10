package tahia;

import tahia.formatter.ThreadSafeCodeFormatter;
import tahia.io.FileWalker;
import tahia.io.FormatterConfigLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TahiaApp {
    public static final Logger LOGGER = Logger.getLogger(TahiaApp.class.getName());

    private final FileWalker fileWalker;
    private final FormatterConfigLoader formatterConfigLoader;

    public TahiaApp() {
        this.fileWalker = new FileWalker();
        this.formatterConfigLoader = new FormatterConfigLoader();
    }

    TahiaApp(
        FileWalker fileWalker,
        FormatterConfigLoader formatterConfigLoader
    ) {
        this.fileWalker = fileWalker;
        this.formatterConfigLoader = formatterConfigLoader;
    }

    public void run(TahiaOptions options) throws IOException {
        final long start = Instant.now().toEpochMilli();
        final var formatterConfig = formatterConfigLoader.getConfig(options.configFile());
        LOGGER.finer("Loaded config");
        final var formatter = new ThreadSafeCodeFormatter(formatterConfig);
        LOGGER.fine("Created formatter");

        fileWalker.findJavaFiles(options.targetFiles())
            .parallel()
            .forEach(formatter::formatFile);

        final long end = Instant.now().toEpochMilli();
        System.out.println(
            "Finished formatting " + formatter.getNumFilesFormatted() + " files in " + (end - start) + "ms"
        );
        final var skipped = formatter.getSkippedFiles();
        if (!skipped.isEmpty()) {
            int maxShown = 15;
            System.err.println(
                "Could not format " + skipped.size() + " files:\n" + skipped.stream()
                    .limit(maxShown)
                    .map(Path::toString)
                    .collect(Collectors.joining("\n"))
            );
            if (skipped.size() > maxShown) {
                System.err.println("(and " + (skipped.size() - maxShown) + " more)");
            }
        }
    }

}
