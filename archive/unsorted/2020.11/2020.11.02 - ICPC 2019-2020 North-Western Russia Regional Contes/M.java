package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class M {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            solveTest(in, out);
        }
    }

    public void solveTest(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int ans = 0;
        for (int j = n - 1; j >= 0; j--) {
            for(int i = 0; i<j; i++) {
                ans += hm.getOrDefault(2 * a[j] - a[i], 0);
            }
            hm.merge(a[j], 1, Integer::sum);
        }
        out.println(ans);
    }
}
