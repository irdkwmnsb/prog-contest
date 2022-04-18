package main;

import main.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class destroy {
    PrintWriter out;

    static private class Edge {
        long w;
        int from, to, ind;

        static public Comparator<Edge> comparator = Comparator.comparing((Edge e) -> e.w);

        public Edge(int from, int to, long w, int ind) {
            this.w = w;
            this.from = from;
            this.to = to;
            this.ind = ind;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int m = in.nextInt();
        long s = in.nextLong();
        List<Edge> l = new ArrayList<>();
        for(int i = 0; i<m; i++) {
            l.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextLong(), i));
        }
        l.sort(Edge.comparator);
        Utils.reverseList(l, 0, l.size() - 1);
        MinMaxDSU dsu = new MinMaxDSU(n);
        boolean[] used = new boolean[m];
        Arrays.fill(used, true);
        for (Edge e : l) {
            MinMaxDSU.MinMaxDSUResult r1 = dsu.get(e.from);
            MinMaxDSU.MinMaxDSUResult r2 = dsu.get(e.to);
            if (r1.getI() != r2.getI()) {
                dsu.merge(r1.getI(), r2.getI());
                used[e.ind] = false;
            }
        }

        IntList ans = new IntList();
        for (int j = m - 1; j >= 0; j--) {
            Edge e = l.get(j);
            if (used[e.ind]) {
                if (s < e.w) {
                    break;
                }
                s -= e.w;
                ans.push(e.ind);
            }
        }

        out.println(ans.length());
        for(int x: ans.asArray()) {
            out.print(x+1);
            out.print(" ");
        }
        out.println();
    }
}
