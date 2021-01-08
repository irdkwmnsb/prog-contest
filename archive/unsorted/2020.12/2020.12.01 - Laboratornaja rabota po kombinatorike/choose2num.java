package main;

import main.Scanner;
import java.io.PrintWriter;
import java.util.stream.IntStream;

public class choose2num {
    PrintWriter out;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = IntStream.generate(in::nextInt).limit(k).toArray();
        out.println(Combinatorics.numberFromCombination(n, k, a));
    }
}
