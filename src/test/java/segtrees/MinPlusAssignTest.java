package segtrees;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinPlusAssignTest {

    @Test
    void simpleMinAssign() {
        Long[] a = {7L, 3L, 9L, 1L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());

        assertEquals(1L, st.query(0, 4));
        st.update(0, 4, 5L);
        assertEquals(5L, st.query(0, 4));
    }

    @Test
    void pointAssign() {
        Long[] a = new Long[10];
        for (int i = 0; i < 10; i++) a[i] = 100L;
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());

        st.update(5, 6, 1L);
        assertEquals(1L, st.query(0, 10));
    }

    @Test
    void overlapAssign() {
        Long[] a = new Long[10];
        for (int i = 0; i < 10; i++) a[i] = (long) i;
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());

        st.update(0, 6, 7L);
        st.update(3, 9, 2L);
        assertEquals(2L, st.query(0, 10));
    }

    @Test
    void fullAssign() {
        Long[] a = {2L, 3L, 4L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());

        st.update(0, 4, 10L);
        assertEquals(10L, st.query(0, 4));
    }

    @Test
    void consecutiveAssigns() {
        Long[] a = {5L, 5L, 5L};
        SegmentTree<Long, Long> st = new SegmentTree<>(a, Combiner.minLongs(), Updater.assignLongs());

        st.update(0, 3, 8L);
        st.update(0, 3, 2L);
        assertEquals(2L, st.query(0, 3));
    }
}
