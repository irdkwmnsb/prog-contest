package main;

import java.io.PrintWriter;
import java.util.Arrays;

public class C {
    PrintWriter out;
    int[][] p;
    StringBuilder ans = new StringBuilder();

    void printAns(int l, int r) {
        if (l == r) {
            out.println(ans.toString());
            return;
        }
        ans.append('0');
        printAns(l, p[l][r]);
        ans.setLength(ans.length() - 1);
        ans.append('1');
        printAns(p[l][r] + 1, r);
        ans.setLength(ans.length() - 1);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] a = in.readIntArray(n);

        int[] sum = new int[n + 1];
        System.arraycopy(a, 0, sum, 1, n);
        Arrays.parallelPrefix(sum, Integer::sum);

        p = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            p[i][i] = i;
        }

        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int len = 1; len < n; len++) {
            for (int left = 0; left + len < n; left++) {
                int right = left + len;
                p[left][right] = p[left][right - 1];
                dp[left][right] = dp[left][p[left][right]] + dp[p[left][right] + 1][right];
                for (int i = p[left][right - 1] + 1; i < p[left + 1][right] + 1; i++) {
                    long newMin = dp[left][i] + dp[i + 1][right];
                    if (newMin <= dp[left][right]) {
                        dp[left][right] = newMin;
                        p[left][right] = i;
                    }
                }
                dp[left][right] += sum[right + 1] - sum[left];
            }
        }

        out.println(dp[0][n - 1]);
        printAns(0, n - 1);
    }
}
