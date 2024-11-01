package tahia.formatter;

import tahia.formatter.jdt.EntireFileCodeFormatter.FileType;

public interface ICodeFormatter {
    public String format(String source, FileType fileType);
}
