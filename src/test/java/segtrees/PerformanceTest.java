package segtrees;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceTest {

    @Test
    void speedLarge() {
        int n = 1_000_000;
        int ops = 1_000_000;

        Long[] init = new Long[n];
        Arrays.fill(init, 0L);

        SegmentTree<Long, Long> st = new SegmentTree<>(
                init,
                Combiner.sumLongs(),
                Updater.addLongs()
        );

        for (int i = 0; i < n; i++) st.update(i, i + 1, 1L);

        Random rnd = new Random(1);
        long t0 = System.nanoTime();
        for (int i = 0; i < ops; i++) {
            int l = rnd.nextInt(n);
            int r = Math.min(n, l + rnd.nextInt(10) + 1);
            if (rnd.nextBoolean()) {
                st.update(l, r, (long) rnd.nextInt(1000));
            } else {
                st.query(l, r);
            }
        }
        long ms = (System.nanoTime() - t0) / 1_000_000;
        assertTrue(ms < 2000, "slow: " + ms + " ms");
    }
}
