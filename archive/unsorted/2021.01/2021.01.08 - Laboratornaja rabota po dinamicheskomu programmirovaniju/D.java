package main;

import main.Scanner;

import java.io.PrintWriter;

public class D {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt();
        long[] w = in.readLongArray(n);
        long[] p = in.readLongArray(n);
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - w[i - 1] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][(int) (j - w[i - 1])] + p[i - 1]);
                }
            }
        }
        IntList ans = new IntList();
        for (int i = n; i >= 1; i--) {
            if (dp[i][m] == 0)
                break;
            if (dp[i - 1][m] != dp[i][m]) {
                m -= w[i - 1];
                ans.push(i);
            }
        }
        out.println(ans.length());
        for (int i = ans.length() - 1; i >= 0; i--) {
            out.print(ans.get(i));
            out.print(" ");
        }
//        out.println(dp[n][m]);
    }
}
