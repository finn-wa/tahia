package tahia.formatter;

import org.eclipse.jdt.core.formatter.CodeFormatter;

enum FileType {
    CODE(CodeFormatter.K_COMPILATION_UNIT | CodeFormatter.F_INCLUDE_COMMENTS),
    MODULE_INFO(CodeFormatter.K_MODULE_INFO | CodeFormatter.F_INCLUDE_COMMENTS);

    public final int kind;

    private FileType(int kind) {
        this.kind = kind;
    }

    public static FileType fromFileName(String name) {
        return name.endsWith("module-info.java")
            ? FileType.MODULE_INFO
            : FileType.CODE;
    }
}
