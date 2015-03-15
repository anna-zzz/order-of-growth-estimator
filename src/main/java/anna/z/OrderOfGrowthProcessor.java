package anna.z;

import com.google.caliper.api.ResultProcessor;
import com.google.caliper.model.Measurement;
import com.google.caliper.model.Trial;
import com.google.common.collect.ImmutableList;
import org.apache.commons.math.stat.descriptive.moment.Mean;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderOfGrowthProcessor implements ResultProcessor {

    private SortedMap<Integer, Double> timings = new TreeMap<>();

    @Override
    public void processTrial(Trial trial) {
        int N = Integer.valueOf(trial.scenario().benchmarkSpec().parameters().get("N"));
        ImmutableList<Measurement> measurements = trial.measurements();
        int size = measurements.size();
        int i = 0;
        double[] values = new double[size];
        double[] weights = new double[size];
        for (Measurement measurement : measurements) {
            values[i] = measurement.value().magnitude();
            weights[i] = measurement.weight();
            i++;
        }
        double mean = new Mean().evaluate(values, weights);

        timings.put(N, mean);
    }

    @Override
    public void close() throws IOException {
        Oh complexity = getComplexity();
        System.out.println("Estimated algorithm complexity: " + complexity.name().toLowerCase());
    }

    public Oh getComplexity() {
        return new OrderOfGrowthEstimator().estimate(timings);
    }

    public SortedMap<Integer, Double> getTimings() {
        return timings;
    }
}
