package tahia.formatter;

import net.lingala.zip4j.ZipFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class FileWalkerTest {
    final FileWalker walker = new FileWalker();

    @TempDir
    Path tempDir;

    @BeforeEach
    void setup() {
        unzipFileTreeIntoTempDir();
    }

    @Test
    void testFindJavaFiles() throws IOException {
        Path dir = tempDir.resolve("tahia");
        assertThat(dir).isNotEmptyDirectory();
        List<Path> files = walker.findJavaFiles(dir.toString()).toList();
        assertThat(files).containsExactlyInAnyOrder(
                dir.resolve("nested1/nestception/DoubleNested.java"),
                dir.resolve("nested1/nestception/DoubleNestedSibling.java"),
                dir.resolve("nested1/Nested1.java"),
                dir.resolve("nested2/nested3/DeepNested.java"),
                dir.resolve("TopLevel1.java"),
                dir.resolve("TopLevel2.java"));
    }

    @Test
    void testFindJavaFilesHandlesSingleFile() throws IOException {
        Path file = tempDir.resolve("tahia/TopLevel1.java");
        assertThat(file).exists();
        assertThat(walker.findJavaFiles(file.toString())).containsExactly(file);
    }

    @Test
    void testFindJavaFilesHandlesSingleDirectoryWithFiles() throws IOException {
        Path dir = tempDir.resolve("tahia/nested1/nestception");
        assertThat(dir)
                .isDirectoryContaining(fileWithName("DoubleNested.java"))
                .isDirectoryContaining(fileWithName("DoubleNestedSibling.java"));
        assertThat(walker.findJavaFiles(dir.toString()))
                .containsExactlyInAnyOrder(
                        dir.resolve("DoubleNested.java"),
                        dir.resolve("DoubleNestedSibling.java"));
    }

    @Test
    void testFindJavaFilesIgnoresNonJavaFiles() throws IOException {
        Path dir = tempDir.resolve("tahia/nested2/nested3");
        assertThat(dir)
                .isDirectoryContaining(fileWithName("DeepNested.java"))
                .isDirectoryContaining(fileWithName("msg.properties"));
        assertThat(walker.findJavaFiles(dir.toString()))
                .containsExactly(dir.resolve("DeepNested.java"));
    }

    @Test
    void testFindJavaFilesHandlesMultipleDirs() throws IOException {
        Path dir1 = tempDir.resolve("tahia/nested1/nestception");
        Path dir2 = tempDir.resolve("tahia/nested2/nested3");
        assertThat(dir1).exists();
        assertThat(dir2).exists();
        assertThat(walker.findJavaFiles(dir1.toString(), dir2.toString()))
                .containsExactlyInAnyOrder(
                        dir1.resolve("DoubleNested.java"),
                        dir1.resolve("DoubleNestedSibling.java"),
                        dir2.resolve("DeepNested.java"));
    }

    @Test
    void testFindJavaFilesHandlesMultipleDirsAndFiles() throws IOException {
        Path dir1 = tempDir.resolve("tahia/nested1/nestception");
        Path dir2 = tempDir.resolve("tahia/nested2/nested3");
        Path file = tempDir.resolve("tahia/TopLevel2.java");
        assertThat(dir1).isNotEmptyDirectory();
        assertThat(dir2).isNotEmptyDirectory();
        assertThat(file).isRegularFile();
        assertThat(walker.findJavaFiles(dir1.toString(), dir2.toString(), file.toString()))
                .containsExactlyInAnyOrder(
                        file,
                        dir1.resolve("DoubleNested.java"),
                        dir1.resolve("DoubleNestedSibling.java"),
                        dir2.resolve("DeepNested.java"));
    }

    @Test
    void testFindJavaFilesIgnoresNonexistentDirs() throws IOException {
        Path dir1 = tempDir.resolve("tahia/nested7");
        Path dir2 = tempDir.resolve("tahia/nested2/nested3");
        assertThat(dir1).doesNotExist();
        assertThat(dir2).exists();
        assertThat(walker.findJavaFiles(dir1.toString(), dir2.toString()))
                .containsExactly(dir2.resolve("DeepNested.java"));
    }

    @Test
    void testFindJavaFilesDeduplicatesResults() throws IOException {
        Path dir = tempDir.resolve("tahia");
        assertThat(dir).isNotEmptyDirectory();
        List<Path> files = walker.findJavaFiles(
                dir.toString(),
                dir.resolve("nested1").toString(),
                dir.resolve("TopLevel1.java").toString()).toList();
        assertThat(files).containsExactlyInAnyOrder(
                dir.resolve("nested1/nestception/DoubleNested.java"),
                dir.resolve("nested1/nestception/DoubleNestedSibling.java"),
                dir.resolve("nested1/Nested1.java"),
                dir.resolve("nested2/nested3/DeepNested.java"),
                dir.resolve("TopLevel1.java"),
                dir.resolve("TopLevel2.java"));
    }

    /**
     * Unzips folders and files to the temp directory. This is the layout of the
     * files:
     * <ul>
     * dir.resolve("nested1/nestception/DoubleNested.java"),
     * dir.resolve("nested1/nestception/DoubleNestedSibling.java"),
     * dir.resolve("nested1/Nested1.java"),
     * dir.resolve("nested1/non-java-file.txt"),
     * dir.resolve("nested2/nested3/emptydir"),
     * dir.resolve("nested2/nested3/DeepNested.java"),
     * dir.resolve("nested2/nested3/msg.properties"),
     * dir.resolve("non-java-file.properties"),
     * dir.resolve("TopLevel1.java"),
     * dir.resolve("TopLevel2.java"),
     * </ul>
     */
    private void unzipFileTreeIntoTempDir() {
        final var classLoader = getClass().getClassLoader();
        try {
            final var path = Path.of(classLoader.getResource("tahia/formatter/file-tree.zip").toURI());
            try (var zip = new ZipFile(path.toString())) {
                zip.extractAll(tempDir.toString());
            }
        } catch (URISyntaxException | IOException e) {
            fail(e);
        }
    }

    private Predicate<Path> fileWithName(String name) {
        return path -> path.getFileName().toString().equals(name);
    }

}
