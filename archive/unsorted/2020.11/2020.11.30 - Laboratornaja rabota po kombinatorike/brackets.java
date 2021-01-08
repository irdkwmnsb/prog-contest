package main;

import main.Scanner;

import java.io.PrintWriter;

public class brackets {
    char[] a;
    int l;
    int n;
    PrintWriter out;

    void generate(int b) {
        if (l == n) {
            for (int i = 0; i < n; i++) {
                out.print(a[i]);
            }
            out.println();
            return;
        }
        if (n - l > b) {
            a[l] = '(';
            l++;
            generate(b + 1);
            l--;
        }
        if (b > 0) {
            a[l] = ')';
            l++;
            generate(b - 1);
            l--;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt() * 2;
        a = new char[n];
        this.out = out;
        generate(0);
    }
}
