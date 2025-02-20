package tahia.io;

import jakarta.annotation.Nullable;
import org.eclipse.jdt.core.JavaCore;
import tahia.formatter.DefaultFormatterConfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/** Loads the config for the Eclipse CodeFormatter. */
public class FormatterConfigLoader {
    public static final String DEFAULT_SOURCE_VERSION = JavaCore.VERSION_21;
    private static final Logger LOGGER = Logger.getLogger(FormatterConfigLoader.class.getName());

    public Map<String, String> getConfig(@Nullable String configFile) throws FileNotFoundException, IOException {
        if (configFile == null) {
            LOGGER.fine("Using default formatter config");
            return DefaultFormatterConfig.CONFIG;
        }
        LOGGER.fine("Loading formatter config from " + configFile);
        return readConfig(configFile);
    }

    /**
     * Return a Java Properties file representing the options that are in the specified configuration file.
     * 
     * @throws IOException
     * @throws FileNotFoundException
     */
    private Map<String, String> readConfig(String filename) throws FileNotFoundException, IOException {
        if (filename.trim().toLowerCase().endsWith("xml")) {
            // We could parse the file using JAXB. Or check how the default Eclipse formatter implementation does it.
            throw new IllegalArgumentException(
                "XML files are not yet supported - please put the settings in a Java properties file"
            );
        }
        LOGGER.fine("Loading formatter configuration");
        File configFile = new File(filename);
        if (!configFile.exists()) {
            throw new FileNotFoundException("The specified config file " + filename + " was not found");
        }
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(configFile))) {
            final Properties formatterOptions = new Properties();
            formatterOptions.load(stream);
            return formatterOptions.stringPropertyNames()
                .stream()
                .collect(Collectors.toMap(k -> k, formatterOptions::getProperty));
        }
    }

}
