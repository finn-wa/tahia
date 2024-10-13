package tahia;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.compiler.env.IModule;
import org.eclipse.jdt.internal.core.util.Util;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Map;
import java.util.Properties;

public class TahiaCodeFormatter {
    private static final String ARG_CONFIG = "-config"; //$NON-NLS-1$
    private static final String ARG_HELP = "-help"; //$NON-NLS-1$
    private static final String ARG_QUIET = "-quiet"; //$NON-NLS-1$
    private static final String ARG_VERBOSE = "-verbose"; //$NON-NLS-1$
    private String configName;
    private Map<Object, Object> options = null;
    private boolean quiet = false;
    private boolean verbose = false;

    /**
     * Display the command line usage message.
     */
    private void displayHelp() {
        System.out.println("glhf");
    }

    private void displayHelp(String message) {
        System.err.println(message);
        System.out.println();
        displayHelp();
    }

    /**
     * Recursively format the Java source code that is contained in the
     * directory rooted at dir.
     */
    private void formatDirTree(File dir, CodeFormatter codeFormatter) {

        File[] files = dir.listFiles();
        if (files == null)
            return;

        for (File file : files) {
            if (file.isDirectory()) {
                formatDirTree(file, codeFormatter);
            } else if (file.getName().endsWith(".java")) {
                formatFile(file, codeFormatter);
            }
        }
    }

    /**
     * Format the given Java source file.
     */
    private void formatFile(File file, CodeFormatter codeFormatter) {
        final Path path = file.toPath();
        IDocument doc = new Document();
        try {
            // read the file
            if (this.verbose) {
                System.out.println("Formatting: " + file.getAbsolutePath());
            }
            String contents = Files.readString(path);
            // format the file (the meat and potatoes)
            doc.set(contents);
            int kind = (file.getName().equals(IModule.MODULE_INFO_JAVA) ? CodeFormatter.K_MODULE_INFO
                    : CodeFormatter.K_COMPILATION_UNIT) | CodeFormatter.F_INCLUDE_COMMENTS;
            TextEdit edit = codeFormatter.format(kind, contents, 0, contents.length(), 0, null);
            if (edit != null) {
                edit.apply(doc);
            } else {
                System.err.println("Failed to format: " + file.getAbsolutePath() + "\nSkipping file.");
                return;
            }
            Files.writeString(path, doc.get());
        } catch (IOException | BadLocationException e) {
            String errorMessage = "Caught " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage(); // $NON-NLS-1$
            Util.log(e, errorMessage);
            System.err.println(errorMessage + "\nSkipping file.");
        }
    }

    private File[] processCommandLine(String[] argsArray) {

        int index = 0;
        final int argCount = argsArray.length;

        final int DEFAULT_MODE = 0;
        final int CONFIG_MODE = 1;

        int mode = DEFAULT_MODE;
        final int INITIAL_SIZE = 1;
        int fileCounter = 0;

        File[] filesToFormat = new File[INITIAL_SIZE];

        loop: while (index < argCount) {
            String currentArg = argsArray[index++];

            switch (mode) {
                case DEFAULT_MODE:
                    if (ARG_HELP.equals(currentArg)) {
                        displayHelp();
                        return null;
                    }
                    if (ARG_VERBOSE.equals(currentArg)) {
                        this.verbose = true;
                        continue loop;
                    }
                    if (ARG_QUIET.equals(currentArg)) {
                        this.quiet = true;
                        continue loop;
                    }
                    if (ARG_CONFIG.equals(currentArg)) {
                        mode = CONFIG_MODE;
                        continue loop;
                    }
                    // the current arg should be a file or a directory name
                    File file = new File(currentArg);
                    if (file.exists()) {
                        if (filesToFormat.length == fileCounter) {
                            System.arraycopy(filesToFormat, 0, (filesToFormat = new File[fileCounter * 2]), 0,
                                    fileCounter);
                        }
                        filesToFormat[fileCounter++] = file;
                    } else {
                        String canonicalPath;
                        try {
                            canonicalPath = file.getCanonicalPath();
                        } catch (IOException e2) {
                            canonicalPath = file.getAbsolutePath();
                        }
                        displayHelp(canonicalPath + "does not exist." + (file.isAbsolute()
                                ? " Please specify only valid Java Source files."
                                : " Please try specifying valid absolute path. "));
                        return null;
                    }
                    break;
                case CONFIG_MODE:
                    this.configName = currentArg;
                    this.options = readConfig(currentArg);
                    if (this.options == null) {
                        displayHelp("A problem occurred while reading the config file " + currentArg);
                        return null;
                    }
                    mode = DEFAULT_MODE;
                    continue loop;
            }
        }
        if (fileCounter == 0) {
            displayHelp("You must specify at least one file or directory to format.");
            return null;
        }
        if (filesToFormat.length != fileCounter) {
            System.arraycopy(filesToFormat, 0, (filesToFormat = new File[fileCounter]), 0, fileCounter);
        }
        return filesToFormat;
    }

    /**
     * Return a Java Properties file representing the options that are in the
     * specified configuration file.
     */
    private Properties readConfig(String filename) {
        File configFile = new File(filename);
        if (!configFile.exists()) {
            return new Properties();
        }
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(configFile))) {
            final Properties formatterOptions = new Properties();
            formatterOptions.load(stream);
            return formatterOptions;
        } catch (IOException e) {
            String canonicalPath = null;
            try {
                canonicalPath = configFile.getCanonicalPath();
            } catch (IOException e2) {
                canonicalPath = configFile.getAbsolutePath();
            }
            String errorMessage;
            if (!configFile.exists() && !configFile.isAbsolute()) {
                errorMessage = "Error reading configuration file (file path : %s, current user directory used to read the file: %s). Try specifying absolute path."
                        .formatted(
                                canonicalPath,
                                System.getProperty("user.dir") //$NON-NLS-1$
                        );

            } else {
                errorMessage = "Error reading configuration file " + canonicalPath;
            }
            Util.log(e, errorMessage);
            System.err.println(errorMessage);
        }
        return null;
    }

    /**
     * Runs the Java code formatter application
     */
    public void start(String[] args) throws Exception {
        final long start = Instant.now().toEpochMilli();
        File[] filesToFormat = processCommandLine(args);

        if (filesToFormat == null) {
            return;
        }

        if (!this.quiet) {
            if (this.configName != null) {
                System.out.println("Configuration Name: " + configName);
            }
            System.out.println("Starting format job ...");
        }

        final CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(this.options,
                ToolFactory.M_FORMAT_EXISTING);
        // format the list of files and/or directories
        for (final File file : filesToFormat) {
            if (file.isDirectory()) {
                formatDirTree(file, codeFormatter);
            } else if (Util.isJavaLikeFileName(file.getPath())) {
                formatFile(file, codeFormatter);
            }
        }
        if (!this.quiet) {
            System.out.println("Done.");
        }
        final long end = Instant.now().toEpochMilli();
        System.out.println("Finished formatting in " + (end - start) + "ms");
    }
}
