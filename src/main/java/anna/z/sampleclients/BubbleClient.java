package anna.z.sampleclients;

import anna.z.AbstractAlgorithm;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.runner.CaliperMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

public class BubbleClient {

    public static void main(String... args) throws InterruptedException {
        CaliperMain.main(BubbleSort.class, args);
    }

    public static class BubbleSort implements AbstractAlgorithm {

        private static final Logger LOG = LoggerFactory.getLogger(BubbleSort.class);

        @Param
        private int N;

        @Benchmark
        public void timeRun() {
            int[] array = new int[N];
            Random random = new Random();
            for (int i = 0; i < N; i++) {
                array[i] = random.nextInt(10000);
            }

            LOG.debug(Arrays.toString(sort(array)));
        }

        private int[] sort(int[] array) {
            boolean sorted = false;
            while (!sorted) {
                boolean changed = false;
                for (int i = 0; i < array.length - 1; i++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        changed = true;
                    }
                }

                sorted = !changed;
            }
            return array;
        }
    }
}
