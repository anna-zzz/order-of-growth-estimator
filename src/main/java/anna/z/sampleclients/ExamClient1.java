package anna.z.sampleclients;

import java.util.SortedMap;
import java.util.TreeMap;

import anna.z.OrderOfGrowthEstimator;

public class ExamClient1 {

    public static void main(String[] args) {

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
        System.out.println(estimator.estimate(data));
    }
}