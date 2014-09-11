package anna.z;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AlgorithmTimer {

    private static final Logger LOG = LoggerFactory.getLogger(AlgorithmTimer.class);

    private final long MAX_TIME;
    private final TimeUnit TIME_UNIT;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    public AlgorithmTimer() {
        MAX_TIME = 5;
        TIME_UNIT = TimeUnit.SECONDS;
    }

    public AlgorithmTimer(int maxTime, TimeUnit timeUnit) {
        MAX_TIME = maxTime;
        TIME_UNIT = timeUnit;
    }

    public SortedMap<Integer, Double> calculateElapsedTime(final AbstractAlgorithm algorithm, int[] Ns) {
        SortedMap<Integer, Double> timings = new TreeMap<>();

        int iteration = 0;
        for (final int N : Ns) {
            try {
                long start = System.nanoTime();

                if (algorithmRunTimesOut(algorithm, N)) break;

                long finish = System.nanoTime();
                double duration = finish - start;
                timings.put(N, duration);
                LOG.info("{}: N = {}, T(N) = {}", iteration, N, duration);
                iteration++;
            } catch (TimeoutException e) {
                LOG.warn("Time to run algorithm for N={} exceeds max time {} {}, killing...",
                        N, MAX_TIME, TIME_UNIT.name().toLowerCase());
                break;
            }
        }
        return timings;
    }

    private boolean algorithmRunTimesOut(final AbstractAlgorithm algorithm, final int N) throws TimeoutException {
        FutureTask<Void> future =
                new FutureTask<>(new Callable<Void>() {
                    public Void call() {
                        algorithm.run(N);
                        return null;
                    }
                });
        executor.execute(future);
        try {
            future.get(MAX_TIME, TIME_UNIT);
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Unexpected exception", e);
            return true;
        } finally {
            // Still called despite of return
            future.cancel(true);
        }
        return false;
    }

    public long getMaxDuration(TimeUnit reqTimeUnit) {
        return TIME_UNIT.convert(MAX_TIME, reqTimeUnit);
    }
}
