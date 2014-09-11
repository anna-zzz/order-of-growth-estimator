package anna.z.sampleclients;

import anna.z.AlgorithmTimer;
import anna.z.Oh;
import anna.z.OrderOfGrowthEstimator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

public class TestClient {

    private static final Logger LOG = LoggerFactory.getLogger(TestClient.class);

    public static void main(String... args) throws InterruptedException {
        BubbleSort algorithm = new BubbleSort();
        AlgorithmTimer timer = new AlgorithmTimer(6, TimeUnit.SECONDS);
        int[] Ns = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000, 512000};
        SortedMap<Integer, Double> timings = timer.calculateElapsedTime(algorithm, Ns);
        LOG.info("Timings: {}", timings.toString());
        OrderOfGrowthEstimator estimator = new OrderOfGrowthEstimator();
        Oh complexity = estimator.estimate(timings);
        LOG.info("Estimated algorithm complexity: {}", complexity.name().toLowerCase());
    }
}
