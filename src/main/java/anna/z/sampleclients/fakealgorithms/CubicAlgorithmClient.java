package anna.z.sampleclients.fakealgorithms;

import anna.z.AbstractAlgorithm;
import anna.z.Constants;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

import static java.lang.Math.pow;

public class CubicAlgorithmClient {

    public static void main(String... args) throws InterruptedException {
        CaliperMain.main(CubicAlgorithm.class, Constants.DEFAULT_ARGS);
    }

    private static class CubicAlgorithm implements AbstractAlgorithm {
        @Param
        private int N;

        @Override
        public void timeRun() throws InterruptedException {
            for (int i = 0; i < pow(N, 3); i++) {
                Thread.sleep(1);
            }
        }
    }
}
