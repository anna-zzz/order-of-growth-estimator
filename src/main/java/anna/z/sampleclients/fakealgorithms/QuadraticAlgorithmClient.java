package anna.z.sampleclients.fakealgorithms;

import anna.z.AbstractAlgorithm;
import anna.z.Constants;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

import static java.lang.Math.pow;

public class QuadraticAlgorithmClient {

    public static void main(String... args) throws InterruptedException {
        CaliperMain.main(QuadraticAlgorithm.class, Constants.DEFAULT_ARGS);
    }

    private static class QuadraticAlgorithm implements AbstractAlgorithm {
        @Param
        private int N;

        @Override
        public void timeRun() throws InterruptedException {
            for (int i = 0; i < pow(N, 2); i++) {
                Thread.sleep(1);
            }
        }
    }
}
