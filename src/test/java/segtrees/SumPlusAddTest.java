package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SumPlusAddTest {

    @Test
    void smallFixedScenario() {
        Long[] a = {1L, 2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());

        assertEquals(9L, st.query(1, 4)); // 2+3+4
        st.update(0, 5, 3L); // +3 ко всем
        assertEquals(15L, st.query(0, 3)); // 4+5+6
    }

    @Test
    void pointUpdateQuery() {
        Long[] a = new Long[10];
        for (int i = 0; i < 10; i++) a[i] = (long) i;
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());

        st.update(5, 6, 100L);
        assertEquals(105L, st.query(5, 6));
    }

    @Test
    void wholeRangeUpdate() {
        Long[] a = new Long[100];
        for (int i = 0; i < 100; i++) a[i] = 1L;
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());

        st.update(0, 100, 2L);
        assertEquals(300L, st.query(0, 100));
    }

    @Test
    void overlapRange() {
        Long[] a = new Long[10];
        for (int i = 0; i < 10; i++) a[i] = 0L;
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());

        st.update(0, 5, 1L);
        st.update(3, 10, 2L);
        assertEquals(3L, st.query(3, 4));
    }

    @Test
    void multipleUpdates() {
        Long[] a = new Long[5];
        for (int i = 0; i < 5; i++) a[i] = 1L;
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.sumLongs(), Updater.addLongs());

        st.update(0, 5, 2L);
        st.update(0, 5, 3L);
        assertEquals(30L, st.query(0, 5));
    }
}
