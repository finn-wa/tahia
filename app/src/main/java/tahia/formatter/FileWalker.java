package tahia.formatter;

import tahia.TahiaApp;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

/** Finds Java files to be formatted. */
public class FileWalker {

    private static final String JAVA_EXTENSION = ".java";

    /**
     * Recursively searches for all Java files in the specified directories or file paths.
     * 
     * @param filesAndDirs paths to files to include and directories to search
     * @return a stream of paths to Java files
     */
    public Stream<Path> findJavaFiles(String... filesAndDirs) {
        // Could potentially be sped up by using FileVisitor to avoid entering
        // directories that have already been searched
        // https://docs.oracle.com/javase/tutorial/essential/io/examples/Find.java
        // could also implement ignore functionality
        return Arrays.stream(filesAndDirs).flatMap(this::findJavaFilesInPath).distinct();
    }

    private Stream<Path> findJavaFilesInPath(String filePath) {
        final var root = Path.of(filePath);
        if (!root.toFile().exists()) {
            TahiaApp.LOGGER.warning("Could not find path " + filePath);
            return Stream.empty();
        }
        try {
            return Files.walk(root, FileVisitOption.FOLLOW_LINKS)
                .filter(path -> Files.isRegularFile(path) && path.toString().endsWith(JAVA_EXTENSION));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
