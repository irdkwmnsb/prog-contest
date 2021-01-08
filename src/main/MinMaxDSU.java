package main;

import java.util.stream.IntStream;

public class MinMaxDSU {
    static class MinMaxDSUResult {
        private final int i, min, max, sz;

        public MinMaxDSUResult(int i, int min, int max, int sz) {
            this.i = i;
            this.min = min;
            this.max = max;
            this.sz = sz;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public int getSz() {
            return sz;
        }

        public int getI() {
            return i;
        }
    }

    final int[] p;
    final int[] mins;
    final int[] maxs;
    final int[] sz;

    public MinMaxDSU(int n) {
        p = IntStream.range(0, n).toArray();
        mins = IntStream.range(0, n).toArray();
        maxs = IntStream.range(0, n).toArray();
        sz = IntStream.generate(() -> 1).limit(n).toArray();
    }

    MinMaxDSUResult get(int x) {
        int root = x;
        while(p[root] != root) {
            root = p[root];
        }
        int i = x;
        int j;
        while(p[i] != i) {
            j = p[i];
            p[i] = root;
            i = j;
        }
        return new MinMaxDSUResult(i, mins[i], maxs[i], sz[i]);
    }

    void merge(int i, int j) {
        i = get(i).getI();
        j = get(j).getI();
        if (i == j)
            return;
        if (sz[j] > sz[i]) {
            int k = i;
            i = j;
            j = k;
        }
        p[j] = i;
        maxs[i] = Integer.max(maxs[i], maxs[j]);
        mins[i] = Integer.min(mins[i], mins[j]);
        sz[i] += sz[j];
    }
}
