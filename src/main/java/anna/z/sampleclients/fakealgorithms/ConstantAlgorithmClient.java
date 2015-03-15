package anna.z.sampleclients.fakealgorithms;

import anna.z.AbstractAlgorithm;
import anna.z.Constants;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

public class ConstantAlgorithmClient {

    public static void main(String... args) throws InterruptedException {
        CaliperMain.main(ConstantAlgorithm.class, Constants.DEFAULT_ARGS);
    }

    private static class ConstantAlgorithm implements AbstractAlgorithm {
        @Param
        private int N;

        @Override
        @Benchmark
        public void timeRun() throws InterruptedException {
            Thread.sleep(1);
        }
    }
}
