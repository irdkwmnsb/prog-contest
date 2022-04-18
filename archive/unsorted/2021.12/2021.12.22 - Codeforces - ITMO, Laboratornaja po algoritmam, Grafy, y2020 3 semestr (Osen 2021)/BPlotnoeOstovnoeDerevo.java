package main;

import main.Scanner;
import main.graphs.WeightedLongEdge;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BPlotnoeOstovnoeDerevo {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt();
        List<WeightedLongEdge> e = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            e.add(new WeightedLongEdge(from, to, w));
        }
        e.sort(WeightedLongEdge.weightComparator);
        long minAns = -1;
        for (int firstEdge = 0; firstEdge < m; firstEdge++) {
            long min = e.get(firstEdge).getW();
            long max = min;
            MinMaxDSU dsu = new MinMaxDSU(n);
            for (int i = firstEdge; i < m; i++) {
                WeightedLongEdge cur = e.get(i);
                MinMaxDSU.MinMaxDSUResult from = dsu.get(cur.getFrom());
                MinMaxDSU.MinMaxDSUResult to = dsu.get(cur.getTo());
                if (from.getSz() == n) {
                    break;
                }
                max = Math.max(cur.getW(), max);
                if (from.getI() != to.getI()) {
                    dsu.merge(from.getI(), to.getI());
                }
            }
            if(dsu.get(0).getSz() == n) {
                if (minAns == -1 || max - min < minAns) {
                    minAns = max - min;
                }
            }
        }
        if(minAns != -1) {
            out.println("YES");
            out.println(minAns);
        } else {
            out.println("NO");
        }
    }
}
