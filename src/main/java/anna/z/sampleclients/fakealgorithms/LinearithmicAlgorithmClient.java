package anna.z.sampleclients.fakealgorithms;

import anna.z.AbstractAlgorithm;
import anna.z.Constants;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

import static java.lang.Math.log;

public class LinearithmicAlgorithmClient {

    public static void main(String... args) throws InterruptedException {
        CaliperMain.main(LinearithmicAlgorithm.class, Constants.DEFAULT_ARGS);
    }

    private static class LinearithmicAlgorithm implements AbstractAlgorithm {
        @Param
        private int N;

        @Override
        public void timeRun() throws InterruptedException {
            for (int i = 0; i < N * log(N); i++) {
                Thread.sleep(1);
            }
        }
    }
}
