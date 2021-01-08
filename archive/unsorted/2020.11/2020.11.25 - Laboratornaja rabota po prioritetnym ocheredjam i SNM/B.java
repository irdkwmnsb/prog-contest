package main;

import main.Scanner;

import java.io.PrintWriter;

public class B {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        MinMaxDSU dsu = new MinMaxDSU(n);
        while (in.hasNextWord()) {
            String op = in.nextWord();
            if (op.charAt(0) == 'u') {
                dsu.merge(in.nextInt() - 1, in.nextInt() - 1);
            } else if (op.charAt(0) == 'g') {
                MinMaxDSU.MinMaxDSUResult res = dsu.get(in.nextInt() - 1);
                out.print(res.getMin() + 1);
                out.print(" ");
                out.print(res.getMax() + 1);
                out.print(" ");
                out.println(res.getSz());
            }
        }
    }
}
