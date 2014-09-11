package anna.z;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public enum Oh {

    // 1
    CONSTANT {
        @Override
        public double applyModel(int N) {
            return 1;
        }
    },

    // log N
    LOGARITHMIC {
        @Override
        public double applyModel(int N) {
            return log(N);
        }
    },

    // N
    LINEAR {
        @Override
        public double applyModel(int N) {
            return N;
        }
    },

    // N log N
    LINEARITHMIC {
        @Override
        public double applyModel(int N) {
            return N * log(N);
        }
    },

    // N^2
    QUADRATIC {
        @Override
        public double applyModel(int N) {
            return pow(N, 2);
        }
    },

    // N^3
    CUBIC {
        @Override
        public double applyModel(int N) {
            return pow(N, 3);
        }
    },

    // 2^N
    EXPONENTIAL {
        @Override
        public double applyModel(int N) {
            return pow(2, N);
        }
    };

    public abstract double applyModel(int N);
}
