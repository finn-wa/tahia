package tahia.formatter;

import jakarta.annotation.Nullable;
import org.eclipse.jdt.core.JavaCore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/** Loads the config for the Eclipse CodeFormatter. */
public class FormatterConfigLoader {
    public static final String DEFAULT_SOURCE_VERSION = JavaCore.VERSION_21;
    private static final Logger LOGGER = Logger.getLogger(FormatterConfigLoader.class.getName());

    public Map<Object, Object> getConfig(@Nullable String configFile)
        throws FileNotFoundException, IOException {
        if (configFile == null) {
            return loadDefaultConfig();
            // return Map.of(
            //     JavaCore.COMPILER_SOURCE,
            //     DEFAULT_SOURCE_VERSION,
            //     JavaCore.COMPILER_COMPLIANCE,
            //     DEFAULT_SOURCE_VERSION,
            //     JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
            //     DEFAULT_SOURCE_VERSION
            // );
        }
        return readConfig(configFile);
    }

    /**
     * Return a Java Properties file representing the options that are in the specified
     * configuration file.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    private Properties readConfig(String filename) throws FileNotFoundException, IOException {
        LOGGER.fine("Loading formatter configuration");
        File configFile = new File(filename);
        if (!configFile.exists()) {
            throw new FileNotFoundException("The specified config file " + filename + " was not found");
        }
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(configFile))) {
            final Properties formatterOptions = new Properties();
            formatterOptions.load(stream);
            return formatterOptions;
        }
    }

    private Properties loadDefaultConfig() throws IOException {
        final Properties options = new Properties();
        try (
            InputStream configStream = getClass().getClassLoader()
                .getResourceAsStream("tahia/formatter/default-config.xml")
        ) {
            options.load(configStream);
        }
        return options;
    }
}
