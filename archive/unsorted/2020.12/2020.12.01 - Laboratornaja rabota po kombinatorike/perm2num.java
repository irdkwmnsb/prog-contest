package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;

public class perm2num {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] perm = IntStream.generate(() -> in.nextInt() - 1).limit(n).toArray();
        out.println(Combinatorics.numberFromPerm(perm));
    }
}
