package main;

import java.io.PrintWriter;
import java.util.*;

public class G {
    static class Query implements Comparable<Query> {
        private final int i;
        private final int j;
        private final int m;

        public Query(int i, int j, int m) {
            this.i = i;
            this.j = j;
            this.m = m;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public int getM() {
            return m;
        }

        @Override
        public int compareTo(Query o) {
            return Integer.compare(m, o.m);
        }
    }

    static {
        Heap<Integer> heap = new Heap<>();
        for (int i : new int[]{1, 2, 3, 4, 3, 2, 1}) {
            heap.push(i);
        }
        while (!heap.isEmpty()) {
            System.err.println(heap.extractBest());
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        Query[] queries = new Query[q];
        for (int i = 0; i < q; i++) {
            queries[i] = new Query(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(queries, Comparator.comparingInt(a -> a.i));
        Heap<Query> heap = new Heap<>();
        int qi = 0;
        for (int i = 1; i <= n; i++) {
            while (qi < q && queries[qi].getI() <= i) {
                heap.push(queries[qi]);
                qi++;
            }
            if (heap.isEmpty()) {
                out.print(Integer.MAX_VALUE);
            } else {
                out.print(heap.getBest().getM());
            }
            out.print(" ");
            while(!heap.isEmpty() && heap.getBest().getJ() <= i) {
                heap.pop();
            }
        }
    }
}
