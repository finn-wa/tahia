package tahia;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Provides the CLI for running TahiaApp. */
public class TahiaCli {
    private static final String OPT_CONFIG = "c";
    private static final String OPT_HELP = "h";
    private static final String OPT_LOG_LEVEL = "l";
    private static final Logger LOGGER = Logger.getLogger(TahiaCli.class.getName());
    private final CommandLineParser cliParser;
    private final HelpFormatter helpFormatter;
    private final Options cliOptions;

    public TahiaCli() {
        this.cliParser = new DefaultParser();
        this.helpFormatter = new HelpFormatter();
        this.cliOptions = getCliOptions();
    }

    TahiaCli(CommandLineParser cliParser, HelpFormatter helpFormatter) {
        this.cliParser = cliParser;
        this.helpFormatter = helpFormatter;
        this.cliOptions = getCliOptions();
    }

    public static void main(String[] args) throws IOException {
        new TahiaCli().run(args);
    }

    public void run(String[] args) throws IOException {
        final var maybeOptions = safelyParseCliArgs(args);
        if (maybeOptions.isEmpty()) {
            return;
        }
        final var options = maybeOptions.get();
        final var formatter = new TahiaApp();
        formatter.run(options);
    }

    Optional<TahiaOptions> safelyParseCliArgs(String[] args) {
        try {
            return Optional.ofNullable(parseCliArgs(args));
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
            printHelp();
        }
        return Optional.empty();
    }

    TahiaOptions parseCliArgs(String[] args) throws ParseException {
        final var cmd = cliParser.parse(cliOptions, args);
        if (cmd.hasOption("help")) {
            printHelp();
            return null;
        }

        final String configFilePath = cmd.getOptionValue("configFile");
        final Level logLevel = Level.parse(cmd.getOptionValue(OPT_LOG_LEVEL, Level.INFO.getName()));
        // Get the remaining arguments (files and directories)
        final String[] targetFiles = cmd.getArgs();
        if (targetFiles.length == 0) {
            throw new ParseException("Specify files or directories to format");
        }
        final boolean useDefaultFormatter = "true".equals(cmd.getOptionValue("d"));
        if (useDefaultFormatter) {
            LOGGER.info("Using default formatter");
        } else {
            LOGGER.info("Using custom formatter");
        }
        return new TahiaOptions(
            targetFiles,
            configFilePath,
            logLevel,
            useDefaultFormatter
        );
    }

    private Options getCliOptions() {
        return new Options()
            .addOption(
                new Option(
                    OPT_CONFIG,
                    "config",
                    false,
                    "Use the formatting style from the specified properties file."
                )
            )
            .addOption(new Option(OPT_LOG_LEVEL, "log-level", true, "Sets log level"))
            .addOption(new Option(OPT_HELP, "help", false, "Displays help message"))
            .addOption(new Option("d", "default", true, "Use default formatter"));
    }

    private void printHelp() {
        helpFormatter.printHelp("tahia", cliOptions);
    }
}
