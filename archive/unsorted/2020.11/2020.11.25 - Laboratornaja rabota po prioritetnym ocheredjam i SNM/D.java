package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;

public class D {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] p = IntStream.range(0, n).toArray();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt() - 1;
            int root = a;
            while (p[root] != root) {
                root = p[root];
            }
            out.print(root + 1);
            out.print(" ");
            p[root] = (root + 1) % n;
            p[a] = p[root];
        }
    }
}