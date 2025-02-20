package tahia;

import java.util.Arrays;
import java.util.logging.Level;

import static java.util.stream.Collectors.joining;

public record TahiaOptions(
    String[] targetFiles,
    String configFile,
    Level logLevel
) {
    @Override
    public final String toString() {
        return "targetFiles=" + Arrays.stream(targetFiles).collect(joining(",", "[", "]")) +
            ", configFile=" + configFile + ", logLevel=" + logLevel;
    }
}
