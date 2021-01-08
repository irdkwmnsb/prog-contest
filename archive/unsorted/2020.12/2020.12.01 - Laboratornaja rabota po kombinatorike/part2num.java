package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;

public class part2num {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int[] part = Arrays.stream(in.nextWord().split("\\+")).mapToInt(Integer::parseInt).toArray();
        int n = Arrays.stream(part).sum();
        long[][] d = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    for (int k = j; k <= i; k++) {
                        d[i][j] += d[i - j][k];
                    }
                } else {
                    d[i][j] = 1;
                }
            }
        }
        int numOfPart = 0;
        int last = 1;
        for (int k : part) {
            for (; last < k; last++) {
                numOfPart += d[n][last];
            }
            n -= last;
        }
        out.println(numOfPart);
    }
}
