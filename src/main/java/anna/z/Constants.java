package anna.z;

public final class Constants {

    private Constants() {
        // NOOP: Utility class
    }

    public static final String[] DEFAULT_ARGS = createDefaultArgsArray();

    private static String[] createDefaultArgsArray() {
        String[] defaultArgs = new String[3];
        // Use special test processor
        defaultArgs[0] = "-Cresults.file.class=anna.z.OrderOfGrowthProcessor";
        // Measure only execution time
        defaultArgs[1] = "--instrument=runtime";
        // Default values for N
        defaultArgs[2] = "-DN=1,2,4,8,16";
        return defaultArgs;
    }
}
