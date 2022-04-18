package main;

import main.Scanner;
import main.geom.Point;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AOstovnoeDerevo {
    PrintWriter out;
    static double INF = 1e9 + 7;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        List<Point> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l.add(Point.readFrom(in));
        }

        double cost = 0;
        boolean[] used = new boolean[n];
        double[] d = new double[n];
        Arrays.fill(d, INF);
        d[0] = 0;

        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            int v = -1;
            for (int x = 0; x < n; x++) {
                if (!used[x] && (v == -1 || d[x] < d[v])) {
                    v = x;
                }
            }

            used[v] = true;
            cost += l.get(v).distanceTo(l.get(p[v]));

            for (int x = 0; x < n; x++) {
                double dist = l.get(x).distanceTo(l.get(v));
                if (dist < d[x]) {
                    d[x] = dist;
                    p[x] = v;
                }
            }
        }
        out.println(cost);
    }
}
