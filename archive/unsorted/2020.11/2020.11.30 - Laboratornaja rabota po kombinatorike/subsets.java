package main;

import main.Scanner;

import java.io.PrintWriter;

public class subsets {
    IntList curSubset = new IntList();
    int n;
    PrintWriter out;

    void generate() {
        for (int i = 0; i < curSubset.length(); i++) {
            out.print(curSubset.get(i));
            out.print(" ");
        }
        out.println();
        for (int i = (curSubset.length() == 0 ? 0 : curSubset.top()) + 1; i <= n; i++) {
            curSubset.push(i);
            generate();
            curSubset.pop();
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        this.out = out;
        generate();
    }
}
