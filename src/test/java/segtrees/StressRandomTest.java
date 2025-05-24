package segtrees;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class StressRandomTest {

    static class Naive {
        long[] a;

        Naive(int n) {
            a = new long[n];
        }

        void update(int l, int r, long val) {
            for (int i = l; i < r; i++) a[i] += val;
        }

        long query(int l, int r) {
            long sum = 0;
            for (int i = l; i < r; i++) sum += a[i];
            return sum;
        }
    }

    @Test
    void stressSumAdd() {
        int n = 500;
        Random rnd = new Random(12345);
        Long[] a = new Long[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextLong(100);

        SegmentTree<Long, Long> tree = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());
        Naive naive = new Naive(n);
        for (int i = 0; i < n; i++) naive.a[i] = a[i];

        for (int i = 0; i < 10000; i++) {
            int l = rnd.nextInt(n);
            int r = rnd.nextInt(n - l) + l + 1;
            if (rnd.nextBoolean()) {
                long x = rnd.nextInt(1000);
                tree.update(l, r, x);
                naive.update(l, r, x);
            } else {
                assertEquals(naive.query(l, r), tree.query(l, r));
            }
        }
    }
}
