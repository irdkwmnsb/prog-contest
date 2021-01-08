package main;

import main.Scanner;

import java.io.PrintWriter;

public class num2choose {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), k = in.nextInt();
        long m = in.nextLong();
        for (int i : Combinatorics.combinationFromNumber(n, k, m)) {
            out.print(i + 1);
            out.print(" ");
        }
        out.println();
    }
}
