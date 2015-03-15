package anna.z.sampleclients.fakealgorithms;

import anna.z.AbstractAlgorithm;
import anna.z.Constants;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;

public class LinearAlgorithmClient {

    public static void main(String... args) throws InterruptedException {
        CaliperMain.main(LinearAlgorithm.class, Constants.DEFAULT_ARGS);
    }

    private static class LinearAlgorithm implements AbstractAlgorithm {
        @Param
        private int N;

        @Override
        public void timeRun() throws InterruptedException {
            for (int i = 0; i < N; i++) {
                Thread.sleep(1);
            }
        }
    }
}
