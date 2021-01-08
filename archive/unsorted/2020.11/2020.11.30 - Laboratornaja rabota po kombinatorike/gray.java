package main;

import main.Scanner;

import java.io.PrintWriter;

public class gray {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        String[] codes = new String[(1 << n)];
        codes[0] = "0";
        codes[1] = "1";
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < (1 << i); j++) {
                codes[j + (1 << i)] = "1" + codes[(1 << i) - j - 1];
            }
            for (int j = 0; j < (1 << i); j++) {
                codes[j] = "0" + codes[j];
            }
        }
        for (String code : codes) {
            out.println(code);
        }
    }
}
