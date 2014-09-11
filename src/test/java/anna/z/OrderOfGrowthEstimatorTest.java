package anna.z;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Math.log;
import static java.lang.Math.pow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderOfGrowthEstimatorTest {

    private static final Logger LOG = LoggerFactory.getLogger(OrderOfGrowthEstimatorTest.class);

    // Algorithm will run until total execution time and N
    // will not exceed provided values in milliseconds
    private static final int MAX_TIME = 60 * 1000;
    private static final int MAX_N = 1000;

    private Oh estimateAlgorithm(AbstractAlgorithm algorithm) {
        SortedMap<Integer, Double> timings = new TreeMap<>();
        OrderOfGrowthEstimator testInstance = new OrderOfGrowthEstimator();

        double totalDuration = 0d;
        int N = 1;
        int iteration = 0;
        while (N < MAX_N && totalDuration < MAX_TIME) {
            LOG.debug(">>> Iteration {}", iteration);

            long start = System.currentTimeMillis();
            algorithm.run(N);
            long finish = System.currentTimeMillis();
            double duration = (double) finish - start;

            timings.put(N, duration);

            LOG.debug("Duration = {} for N = {}", duration, N);

            N++;
            totalDuration += duration;
            iteration++;

            LOG.debug("Total duration: {}ms, {}s", totalDuration, totalDuration / 1000);
        }
        LOG.debug("Max N: {}", N);
        return testInstance.estimate(timings);
    }

    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            fail("InterruptedException incoming");
        }
    }

    @Test
    public void shouldReportConstant() {
        LOG.debug("Constant: ");
        AbstractAlgorithm constant = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                sleep();
            }
        };
        assertEquals(Oh.CONSTANT, estimateAlgorithm(constant));
    }

    @Test
    public void shouldReportLogarithmic() {
        LOG.debug("Logarithmic: ");
        AbstractAlgorithm logarithmic = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                for (int i = 0; i < log(N); i++) {
                    sleep();
                }
            }
        };
        assertEquals(Oh.LOGARITHMIC, estimateAlgorithm(logarithmic));
    }

    @Test
    public void shouldReportLinear() {
        LOG.debug("Linear: ");
        AbstractAlgorithm linear = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                for (int i = 0; i < N; i++) {
                    sleep();
                }
            }
        };
        assertEquals(Oh.LINEAR, estimateAlgorithm(linear));
    }

    @Test
    public void shouldReportLinearithmic() {
        LOG.debug("Linearithmic: ");
        AbstractAlgorithm linearithmic = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                for (int i = 0; i < N * log(N); i++) {
                    sleep();
                }
            }
        };
        assertEquals(Oh.LINEARITHMIC, estimateAlgorithm(linearithmic));
    }

    @Test
    public void shouldReportQuadratic() {
        LOG.debug("Quadratic: ");
        AbstractAlgorithm quadratic = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                for (int i = 0; i < pow(N, 2); i++) {
                    sleep();
                }
            }
        };
        assertEquals(Oh.QUADRATIC, estimateAlgorithm(quadratic));
    }

    @Test
    public void shouldReportCubic() {
        LOG.debug("Cubic: ");
        AbstractAlgorithm cubic = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                for (int i = 0; i < pow(N, 3); i++) {
                    sleep();
                }
            }
        };
        assertEquals(Oh.CUBIC, estimateAlgorithm(cubic));
    }

    @Test
    public void shouldReportExponential() {
        LOG.debug("Exponential: ");
        AbstractAlgorithm exponential = new AbstractAlgorithm() {
            @Override
            public void run(int N) {
                for (int i = 0; i < pow(2, N); i++) {
                    sleep();
                }
            }
        };
        assertEquals(Oh.EXPONENTIAL, estimateAlgorithm(exponential));
    }
}
