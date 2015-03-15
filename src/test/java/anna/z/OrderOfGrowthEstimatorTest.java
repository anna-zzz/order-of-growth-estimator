package anna.z;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class OrderOfGrowthEstimatorTest {

    @Test
    public void shouldEstimateCubic() {
        SortedMap<Integer, Double> data = new TreeMap<Integer, Double>() {
            {
                put(128, 0.073);
                put(256, 0.874);
                put(512, 10.050);
                put(1024, 123.857);
                put(2048, 1472.686);
            }
        };

        OrderOfGrowthEstimator estimator = new OrderOfGrowthEstimator();
        Oh actual = estimator.estimate(data);
        assertEquals(Oh.CUBIC, actual);
    }

    @Test
    public void shouldEstimateLinear() {
        SortedMap<Integer, Double> data = new TreeMap<Integer, Double>() {
            {
                put(10, 3.0);
                put(100, 6.0);
                put(1000, 17.0);
                put(10000, 51.0);
            }
        };

        OrderOfGrowthEstimator estimator = new OrderOfGrowthEstimator();
        Oh actual = estimator.estimate(data);
        assertEquals(Oh.LINEAR, actual);
    }
}
