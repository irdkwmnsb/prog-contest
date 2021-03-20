package main;

import java.io.PrintWriter;
import java.util.stream.IntStream;

public class J {
    PrintWriter out;

    boolean has(char c, String a, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (a.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    String findOrEmpty(char c, String a, int from, int to) {
        if (has(c, a, from, to)) {
            return String.valueOf(c);
        } else {
            return "";
        }
    }

    String findInSubstring(String a, int aL, int aR, String b, int bL, int bR) {
        System.err.printf("%d %d %d %d%n", aL, aR, bL, bR);
        if (aL == aR) {
            return findOrEmpty(a.charAt(aL - 1), b, bL - 1, bR - 1);
        }
        if (bL == bR) {
            return findOrEmpty(b.charAt(bL - 1), a, aL - 1, aR - 1);
        }
        int m = (aL + aR) / 2;
        int[][] dp1 = new int[b.length() + 2][2]; // If this solution MLs I will have to move these arrays to another function to have them destroyed before the recursive call.
        for (int i = aL; i <= m; i++) {
            for (int j = bL; j <= bR; j++) {
                dp1[j][0] = dp1[j][1];
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    dp1[j][1] = Math.max(dp1[j - 1][1], dp1[j][0]);
                } else {
                    dp1[j][1] = dp1[j - 1][0] + 1;
                }
            }
        }
        int[][] dp2 = new int[b.length() + 2][2];
        for (int i = aR; m < i; i--) {
            for (int j = bR; bL <= j; j--) {
                dp2[j][0] = dp2[j][1];
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    dp2[j][1] = Math.max(dp2[j + 1][1], dp2[j][0]);
                } else {
                    dp2[j][1] = dp2[j + 1][0] + 1;
                }
            }
        }

        int p = IntStream.range(bL - 1, bR + 1).reduce((i, j) -> (dp1[i][1] + dp2[i + 1][1] > dp1[j][1] + dp2[j + 1][1] ? i : j)).orElseThrow();

        return findInSubstring(a, aL, m, b, bL, p) + findInSubstring(a, m + 1, aR, b, p + 1, bR);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String a = in.nextWord();
        String b = in.nextWord();
        out.println(findInSubstring(a, 1, a.length(), b, 1, b.length()));
    }
}
