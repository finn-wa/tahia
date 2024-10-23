package tahia.formatter.jdt;

import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tahia.formatter.jdt.EntireFileCodeFormatter.FileType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class EntireFileCodeFormatterTest {

    @ParameterizedTest
    @MethodSource("getTestJavaFiles")
    void testFormatJavaFileMatchesDefaultFormatter(String filename, String content) {
        assertThatFormatterMatchesDefaultFormatter(content, FileType.CODE);
    }

    static Stream<String[]> getTestJavaFiles() throws IOException {
        return listFilesAndContentAsArgs("tahia/formatter/testdata");
    }

    @ParameterizedTest
    @MethodSource("getTestJavadocFiles")
    void testFormatJavadocsMatchesDefaultFormatter(String filename, String content) {
        assertThatFormatterMatchesDefaultFormatter(content, FileType.CODE);
    }

    static Stream<String[]> getTestJavadocFiles() throws IOException {
        return listFilesAndContentAsArgs("tahia/formatter/testjavadoc");
    }

    @ParameterizedTest
    @MethodSource("getTestImportFiles")
    void testFormatImportsMatchesDefaultFormatter(String filename, String content) {
        assertThatFormatterMatchesDefaultFormatter(content, FileType.CODE);
    }

    static Stream<String[]> getTestImportFiles() throws IOException {
        return listFilesAndContentAsArgs("tahia/formatter/testimports");
    }

    @ParameterizedTest
    @MethodSource("getTestModuleInfoFiles")
    void testFormatModuleInfoMatchesDefaultFormatter(String filename, String content) {
        assertThatFormatterMatchesDefaultFormatter(content, FileType.MODULE_INFO);
    }

    static Stream<String[]> getTestModuleInfoFiles() throws IOException {
        return listFilesAndContentAsArgs("tahia/formatter/testmoduleinfo");
    }

    void assertThatFormatterMatchesDefaultFormatter(String content, FileType fileType) {
        final var config = DefaultFormatterConfig.CONFIG;
        final var formatter = new EntireFileCodeFormatter(new DefaultCodeFormatterOptions(config));
        final var expectedResult = applyJdtFormatter(config, content, fileType);
        final String actualResult = formatter.format(content, FileType.CODE);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    String applyJdtFormatter(Map<String, String> config, String source, FileType fileType) {
        final var defaultFormatter = new DefaultCodeFormatter(new DefaultCodeFormatterOptions(config));
        final TextEdit edit = defaultFormatter.format(
            fileType.kind,
            source,
            new IRegion[] { new Region(0, source.length()) },
            0,
            null
        );
        final var doc = new EditableDocumentStub(source);
        try {
            edit.apply(doc, TextEdit.NONE);
        } catch (MalformedTreeException | BadLocationException e) {
            fail(e);
        }
        return doc.get();
    }

    static Stream<String[]> listFilesAndContentAsArgs(String relativeDir) throws IOException {
        final var dir = resolvePath(relativeDir);
        return Files.list(dir).map(file -> {
            try {
                return new String[] { dir.relativize(file).toString(), Files.readString(file) };
            } catch (IOException e) {
                return fail(e);
            }
        });
    }

    static Path resolvePath(String relativePath) {
        URI uri = null;
        try {
            uri = EntireFileCodeFormatter.class.getClassLoader().getResource(relativePath).toURI();
        } catch (URISyntaxException e) {
            return fail(e);
        }
        final var path = Path.of(uri);
        assertThat(path).exists();
        return path;
    }

}
