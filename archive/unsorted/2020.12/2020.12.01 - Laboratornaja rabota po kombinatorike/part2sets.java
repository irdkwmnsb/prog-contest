package main;

import main.Scanner;

import java.io.PrintWriter;

public class part2sets {
    int[] used;
    int pos = 0;
    int numUsed = 0;
    int n, k;
    PrintWriter out;

    void generate() {
        if (pos == n) {
            if (numUsed != k)
                return;
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < n; j++) {
                    if (used[j] == i) {
                        out.print(j + 1);
                        out.print(" ");
                    }
                }
                out.println();
            }
            out.println();
        } else {
            for (int i = 0; i <= numUsed; i++) {
                used[pos] = i;
                pos++;
                if (i == numUsed) {
                    numUsed++;
                    generate();
                    numUsed--;
                } else {
                    generate();
                }
                pos--;
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt();
        k = in.nextInt();
        used = new int[n];
        generate();
    }
}
