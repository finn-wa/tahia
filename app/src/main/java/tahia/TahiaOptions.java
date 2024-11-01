package tahia;

import java.util.logging.Level;

public record TahiaOptions(
    String[] targetFiles,
    String configFile,
    Level logLevel,
    boolean useDefaultFormatter
) {}
