package main;

import main.Scanner;

import java.io.PrintWriter;

public class brackets2num {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String brackets = in.nextWord();
        int n = brackets.length() / 2;
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
        int bal = 0;
        long ans = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (brackets.charAt(i) == '(') {
                bal++;
            } else {
                ans += kt[2 * n - (i + 1)][bal + 1];
                bal--;
            }
        }
        out.println(ans);
    }
}
