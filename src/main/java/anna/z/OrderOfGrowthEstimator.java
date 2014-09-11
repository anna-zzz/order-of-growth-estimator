package anna.z;

import org.la4j.linear.LinearSystemSolver;
import org.la4j.matrix.Matrix;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.la4j.vector.Vector;
import org.la4j.vector.dense.BasicVector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static org.la4j.LinearAlgebra.DENSE_FACTORY;
import static org.la4j.LinearAlgebra.LEAST_SQUARES;

public class OrderOfGrowthEstimator {

    private static final Logger LOG = LoggerFactory.getLogger(OrderOfGrowthEstimator.class);
    private static final int MIN_ITERATIONS = 4;
    private static final int LEAST_ITERATIONS = 10;

    public Oh estimate(SortedMap<Integer, Double> timings) {
        int timingsQuantity = timings.size();
        if (timingsQuantity < MIN_ITERATIONS) {
            LOG.error("Refusing to estimate {} timings, at least {} required!",
                    timingsQuantity, LEAST_ITERATIONS);
            System.exit(1);
        }

        // Dumb initialization with worst stuff ever
        Oh minResidualOh = Oh.EXPONENTIAL;
        double minResidual = Double.MAX_VALUE;

        for (Oh oh : Oh.values()) {
            double c = findC(timings, oh);
            double sum = 0d;
            for (Map.Entry<Integer, Double> entry : timings.entrySet()) {
                // Observed T
                double T = entry.getValue();
                // Estimated T*
                double estT = c * oh.applyModel(entry.getKey());
                // Residual (T - T*)^2
                sum += T * T - 2 * T * estT + estT * estT;
            }

            // Mean residual for current Oh type
            double residual = sum / timingsQuantity;

            if (residual <= minResidual) {
                minResidual = residual;
                minResidualOh = oh;
            }
        }
        return minResidualOh;
    }

    private double findC(SortedMap<Integer, Double> timings, Oh oh) {
        // http://en.wikipedia.org/wiki/Least_squares#Problem_statement

        // Independent Ns set
        Matrix x = createX(timings.keySet(), oh);
        // Observed timings set
        Vector y = createY(timings.values());

        LinearSystemSolver solver = x.withSolver(LEAST_SQUARES);
        // Adjustable parameter
        Vector beta = solver.solve(y, DENSE_FACTORY);

        // Vector beta always has 1 element
        return beta.get(0);
    }

    private Matrix createX(Set<Integer> keys, Oh oh) {
        double[] keysArray = new double[keys.size()];
        int i = 0;
        // Apply model to each N
        for (int d : keys)
            keysArray[i++] = oh.applyModel(d);

        return new Basic1DMatrix(keysArray.length, 1, keysArray);
    }

    private Vector createY(Collection<Double> values) {
        double[] valuesArray = new double[values.size()];
        int i = 0;
        for (double d : values)
            valuesArray[i++] = d;
        return new BasicVector(valuesArray);
    }
}
