package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L {
    int n, k;
    int[] v;
    int[] w;

    int[] calcInds(double[] a, double x) {
        for (int i = 0; i < n; i++) {
            a[i] = v[i] - x * w[i];
        }
        int[] inds = IntStream.range(0, n).toArray();
        Sorts.sort(inds, (int i, int j) -> -Double.compare(a[i], a[j]));
        return inds;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        v = new int[n];
        w = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = in.nextInt();
            w[i] = in.nextInt();
        }
        double[] a = new double[n];
        double m = Utils.upperBound(0., 1e7, (double x) -> {
            int[] inds = calcInds(a, x);
            double s = 0;
            for (int i = 0; i < k; i++)
                s += a[inds[i]];
            return s >= 1e-6;
        }).orElse(0);
        System.err.println(m);
        int[] inds = calcInds(a, m);
        out.println(IntStream.of(inds).limit(k).map((i) -> i + 1).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
