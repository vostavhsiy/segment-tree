package segtrees;

public interface Updater<T, U> {
    T apply(T value, U update, int length);
    U compose(U oldUpdate, U newUpdate);
    U identity();

    static Updater<Long, Long> addLongs() {
        return new Updater<>() {
            public Long apply(Long value, Long update, int length) {
                return value + update * length;
            }
            public Long compose(Long oldUpdate, Long newUpdate) {
                return oldUpdate + newUpdate;
            }
            public Long identity() {
                return 0L;
            }
        };
    }

    static Updater<Long, Long> assignLongs() {
        return new Updater<>() {
            public Long apply(Long value, Long update, int length) {
                return update;
            }
            public Long compose(Long oldUpdate, Long newUpdate) {
                return newUpdate;
            }
            public Long identity() {
                return null;
            }
        };
    }

}
