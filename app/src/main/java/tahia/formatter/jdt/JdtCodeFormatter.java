package tahia.formatter.jdt;

import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatterOptions;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import tahia.formatter.ICodeFormatter;
import tahia.formatter.jdt.EntireFileCodeFormatter.FileType;

public class JdtCodeFormatter implements ICodeFormatter {

    private final DefaultCodeFormatter formatter;

    public JdtCodeFormatter(DefaultCodeFormatterOptions options) {
        this.formatter = new DefaultCodeFormatter(options);
    }

    @Override
    public String format(String source, FileType fileType) {
        final TextEdit edit = formatter.format(
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
            throw new IllegalStateException(e);
        }
        return doc.get();
    }

}
