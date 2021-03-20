package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class L {
    PrintWriter out;

    static class Edge {
        public int from, to;
        public long w;

        public Edge(int from, int to, long w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", w=" + w +
                    '}';
        }
    }

    static class CalcResult {
        public long can, cannot;

        public CalcResult(int can, int cannot) {
            this.can = can;
            this.cannot = cannot;
        }

        public long getMax() {
            return Math.max(can, cannot);
        }

        @Override
        public String toString() {
            return "CalcResult{" +
                    "can=" + can +
                    ", cannot=" + cannot +
                    '}';
        }
    }

    List<Edge>[] g;

    CalcResult findAnsForSubtree(int v, int p) {
        CalcResult res = new CalcResult(0, 0);

        for (Edge to : g[v]) {
            if (to.to == p) {
                continue;
            }
            CalcResult subtree = findAnsForSubtree(to.to, v);
            res.can = Math.max(res.can, subtree.cannot + to.w - subtree.getMax());
            res.cannot += subtree.getMax();
        }
        res.can += res.cannot;
        return res;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        g = Stream.generate(ArrayList<Edge>::new).limit(n).toArray((IntFunction<List<Edge>[]>) List[]::new); }
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            g[from].add(new Edge(from, to, w));
            g[to].add(new Edge(to, from, w));
        }
        out.println(findAnsForSubtree(0, -1).getMax());
    }
}
