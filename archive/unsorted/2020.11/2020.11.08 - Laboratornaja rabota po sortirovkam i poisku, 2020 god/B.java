package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class B {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = IntStream.generate(in::nextInt).limit(n).toArray();
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int req = in.nextInt();
            int lb = Utils.lowerBound(a, req);
            int ub = Utils.upperBound(a, req);
            if (a[lb] != req || a[ub] != req) {
                lb = -1;
                ub = -1;
            } else {
                lb = lb + 1;
                ub = ub + 1;
            }
            out.println(String.format("%d %d", lb, ub));
        }
    }
}
