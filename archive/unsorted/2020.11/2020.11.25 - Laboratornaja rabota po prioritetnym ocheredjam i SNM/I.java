package main;

import main.Scanner;

import java.io.PrintWriter;

public class I {
    PrintWriter out;

    public void print(int x, int y, int z) {
        out.print(x);
        out.print(" ");
        out.print(y);
        out.print(" ");
        out.println(z);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt();
        if (m <= 3) {
            for (int i = 2; i <= m; i++) {
                print(1, 1, i);
            }
            print(0, Integer.max(1, m - 1), m);
        } else {
            print(1, 3, 1);
            print(1, 3, 2);
            m -= 2;
            for (int i = 3; m > 0; i++) {
                print(1, i + 1, i);
                m--;
                for (int j = 2 - i % 2; j + 1 < i && m > 0; j += 2, m--) {
                    print(0, j, j + 1);
                }
            }
        }
    }
}
