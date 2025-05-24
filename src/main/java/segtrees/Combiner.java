package segtrees;

import java.util.function.BiFunction;

public interface Combiner<T> {
    T combine(T a, T b);
    T neutral();

    static Combiner<Long> sumLongs() {
        return new Combiner<>() {
            public Long combine(Long a, Long b) { return a + b; }
            public Long neutral() { return 0L; }
        };
    }

    static Combiner<Long> minLongs() {
        return new Combiner<>() {
            public Long combine(Long a, Long b) { return Math.min(a, b); }
            public Long neutral() { return Long.MAX_VALUE; }
        };
    }
}
