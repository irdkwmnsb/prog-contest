package main;

import main.Scanner;

import java.io.PrintWriter;

public class K {
    PrintWriter out;
    static long MOD = 1000000000;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        long[][] dp = new long[n][n];
        for (int len = 1; len <= n; len++) {
            for (int start = 0; start + len - 1 < n; start++) {
                int end = start + len - 1;
                if (len == 1) {
                    dp[start][end] = 1;
                } else {
                    if (a[start] == a[end]) {
                        dp[start][end] = (dp[start + 1][end] + dp[start][end - 1] + 1) % MOD;
                    } else {
                        dp[start][end] = ((dp[start + 1][end] + dp[start][end - 1]) % MOD + MOD - dp[start + 1][end - 1]) % MOD;
                    }
                }
            }
        }
        out.println(dp[0][n - 1]);
    }
}
