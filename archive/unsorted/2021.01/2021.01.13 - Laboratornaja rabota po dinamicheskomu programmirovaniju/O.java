package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.OptionalInt;

public class O {
    PrintWriter out;
    int[][][] dp;

    void calcDP(int x, int y, int a, int b) {
        dp = new int[500][500][2];
        for (int i = 0; i <= x; i++) {
            dp[i][y + 1][1] = -b;
        }
        for (int i = 0; i <= y; i++) {
            dp[x + 1][i][1] = -a;
        }
    }

    boolean isPossible(int n, int x, int y, int a, int b, int l) {
        for (int i = x; i >= 0; i--) {
            for (int j = y; j >= 0; j--) {
                int[] f = Arrays.copyOf(dp[i + 1][j], 2);
                int[] s = Arrays.copyOf(dp[i][j + 1], 2);

                f[1] += a;
                if (f[1] >= n) {
                    f[0]++;
                    f[1] = 0;
                }

                s[1] += b;
                if (s[1] >= n) {
                    s[0]++;
                    s[1] = 0;
                }

                if (f[0] > s[0] || (f[0] == s[0] && f[1] > s[1])) {
                    dp[i][j] = f;
                } else {
                    dp[i][j] = s;
                }
            }
        }
        return dp[0][0][0] >= l;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int x = in.nextInt(), a = in.nextInt(), y = in.nextInt(), b = in.nextInt(), l = in.nextInt();
        calcDP(x, y, a, b);
        OptionalInt ans = Utils.upperBound(0, 1000000, (int n) -> isPossible(n, x, y, a, b, l));
        out.println(ans.orElse(-1));
    }
}
