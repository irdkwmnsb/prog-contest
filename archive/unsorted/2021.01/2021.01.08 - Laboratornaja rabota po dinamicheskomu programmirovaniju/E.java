package main;

import main.Scanner;

import java.io.PrintWriter;

public class E {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        char[] a = in.nextWord().toCharArray();
        int n = a.length;
        char[] b = in.nextWord().toCharArray();
        int m = b.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        out.println(dp[n][m]);
    }
}
