package anna.z.sampleclients;

import java.util.SortedMap;
import java.util.TreeMap;

import anna.z.OrderOfGrowthEstimator;

public class ExamClient2 {

    public static void main(String[] args) {

        SortedMap<Integer, Double> data = new TreeMap<Integer, Double>() {
            {
                put(10, 3.0);
                put(100, 6.0);
                put(1000, 17.0);
                put(10000, 51.0);
            }
        };

        OrderOfGrowthEstimator estimator = new OrderOfGrowthEstimator();
        System.out.println(estimator.estimate(data));
    }
}