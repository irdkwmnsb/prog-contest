package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;

public class A {
    PrintWriter out;
    int[][] from;

    String getAns(int left, int right) {
        if (left == right) {
            return "A";
        }
        return "(" + getAns(left, from[left][right]) + getAns(from[left][right] + 1, right) + ")";
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        long[] l = new long[n];
        long[] r = new long[n];
        for (int i = 0; i < n; i++) {
            l[i] = in.nextInt();
            r[i] = in.nextInt();
        }
        long[][] dp = new long[n][n]; // dp[i][j] - answer for [l, r]
        for (long[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        from = new int[n][n];
        for (int len = 1; len < n; len++) { // How many matrices are we joining
            for (int left = 0; left + len < n; left++) { // left
                int right = left + len;
                for (int middle = left; middle < right; middle++) { // middle
                    long new_val = dp[left][middle] + dp[middle + 1][right] + l[left] * r[middle] * r[right];
                    if (new_val < dp[left][right]) {
                        dp[left][right] = new_val;
                        from[left][right] = middle;
                    }
                }
            }
        }
        out.println(getAns(0, n - 1));
    }
}
