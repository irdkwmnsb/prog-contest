package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

public class F {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String patternS = in.nextWord();
        char[] pattern = patternS.toCharArray();
        int n = pattern.length;
        char[] word = in.nextWord().toCharArray();
        int m = word.length;
        boolean[] dp = new boolean[m + 1];
        boolean[] newDp = new boolean[m + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            newDp[0] = pattern[i - 1] == '*' && dp[0];
            for (int j = 1; j <= m; j++) {
                if (pattern[i - 1] == '?' || pattern[i - 1] == word[j - 1]) {
                    newDp[j] = dp[j - 1];
                } else if (pattern[i - 1] == '*') {
                    newDp[j] = dp[j] || newDp[j - 1];
                }
            }
            System.arraycopy(newDp, 0, dp, 0, m + 1);
            Arrays.fill(newDp, false);
//            System.err.println(Arrays.toString(dp));
        }
        boolean allStars = patternS.chars().allMatch((c) -> c == '*');
        if ((n == 0 && m == 0) || allStars || (n != 0 && dp[m])) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
