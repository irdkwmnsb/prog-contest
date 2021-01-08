package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;

public class num2brackets {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        long k = in.nextLong();
        long[][] kt = new long[2 * n + 1][2 * n + 1];
        kt[0][0] = 1;
        for (int i = 1; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                kt[i][j] = kt[i - 1][j + 1];
                if (j != 0) {
                    kt[i][j] += kt[i - 1][j - 1];
                }
            }
        }
        int depth = 0;
        char[] ans = new char[n*2];
        for (int i = 0; i < 2 * n; i++) {
            if (kt[2 * n - (i + 1)][depth + 1] > k) {
                ans[i] = '(';
                depth++;
            } else {
                k -= kt[2 * n - i - 1][depth + 1];
                ans[i] = ')';
                depth--;
            }
        }
        out.println(String.valueOf(ans));
    }
}
