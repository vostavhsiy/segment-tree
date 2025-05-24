package segtrees;

import java.util.Arrays;

public class SegmentTree<T, U> {
    private final int n;
    private final T[] tree;
    private final U[] lazy;
    private final boolean[] pending;
    private final Combiner<T> combiner;
    private final Updater<T, U> updater;

    @SuppressWarnings("unchecked")
    public SegmentTree(T[] array, Combiner<T> combiner, Updater<T, U> updater) {
        this.n = array.length;
        this.combiner = combiner;
        this.updater = updater;

        tree = (T[]) new Object[4 * n];
        lazy = (U[]) new Object[4 * n];
        pending = new boolean[4 * n];

        build(array, 1, 0, n);
    }

    private void build(T[] array, int v, int l, int r) {
        if (r - l == 1) {
            tree[v] = array[l];
        } else {
            int m = (l + r) / 2;
            build(array, v * 2, l, m);
            build(array, v * 2 + 1, m, r);
            tree[v] = combiner.combine(tree[v * 2], tree[v * 2 + 1]);
        }
        lazy[v] = updater.identity();
        pending[v] = false;
    }

    private void push(int v, int l, int r) {
        if (!pending[v]) return;
        int m = (l + r) / 2;
        apply(v * 2, lazy[v], m - l);
        apply(v * 2 + 1, lazy[v], r - m);
        lazy[v] = updater.identity();
        pending[v] = false;
    }

    private void apply(int v, U val, int length) {
        if (val == null) return;
        tree[v] = updater.apply(tree[v], val, length);
        if (length > 1) {
            if (!pending[v]) {
                lazy[v] = val;
            } else {
                lazy[v] = updater.compose(lazy[v], val);
            }
            pending[v] = true;
        }
    }

    public void update(int l, int r, U val) {
        update(1, 0, n, l, r, val);
    }

    private void update(int v, int tl, int tr, int l, int r, U val) {
        if (l >= r) return;
        if (l == tl && r == tr) {
            apply(v, val, tr - tl);
        } else {
            push(v, tl, tr);
            int tm = (tl + tr) / 2;
            update(v * 2, tl, tm, l, Math.min(r, tm), val);
            update(v * 2 + 1, tm, tr, Math.max(l, tm), r, val);
            tree[v] = combiner.combine(tree[v * 2], tree[v * 2 + 1]);
        }
    }

    public T query(int l, int r) {
        return query(1, 0, n, l, r);
    }

    private T query(int v, int tl, int tr, int l, int r) {
        if (l >= r) return combiner.neutral();
        if (l == tl && r == tr) return tree[v];
        push(v, tl, tr);
        int tm = (tl + tr) / 2;
        return combiner.combine(
                query(v * 2, tl, tm, l, Math.min(r, tm)),
                query(v * 2 + 1, tm, tr, Math.max(l, tm), r)
        );
    }
}
