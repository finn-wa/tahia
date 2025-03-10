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

/** Provides the CLI for running TahiaApp. */
public class TahiaCli {
    private static final String OPT_INPUT = "i";
    private static final String OPT_PREFS = "p";
    private static final String OPT_HELP = "h";
    private static final String OPT_LOG_LEVEL = "l";
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

        final String configFilePath = cmd.getOptionValue(OPT_PREFS);
        final Level logLevel = Level.parse(cmd.getOptionValue(OPT_LOG_LEVEL, Level.INFO.getName()));
        // Get the remaining arguments (files and directories)
        final String[] targetFiles = cmd.getOptionValues(OPT_INPUT);
        if (targetFiles.length == 0) {
            throw new ParseException("Specify files or directories to format");
        }
        return new TahiaOptions(
            targetFiles,
            configFilePath,
            logLevel
        );
    }

    private Options getCliOptions() {
        return new Options()
            .addOption(
                Option.builder(OPT_INPUT)
                    .longOpt("input")
                    .hasArgs()
                    .desc("Paths to files & directories to format")
                    .required()
                    .build()
            )
            .addOption(
                new Option(
                    OPT_PREFS,
                    "prefs",
                    true,
                    "Use the formatting style from the specified properties file."
                )
            )
            .addOption(new Option(OPT_LOG_LEVEL, "log-level", true, "Sets log level"))
            .addOption(new Option(OPT_HELP, "help", false, "Displays help message"));
    }

    private void printHelp() {
        helpFormatter.printHelp("tahia", cliOptions);
    }
}
