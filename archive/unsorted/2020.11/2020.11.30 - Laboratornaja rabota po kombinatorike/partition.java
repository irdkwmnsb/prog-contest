package main;

import main.Scanner;

import java.io.PrintWriter;

public class partition {
    int n;
    IntList sl = new IntList();
    PrintWriter out;
    int curSum = 0;

    void generate() {
        if (curSum == n) {
            for (int i = 0; i < sl.length(); i++) {
                if (i > 0)
                    out.print("+");
                out.print(sl.get(i));
            }
            out.println();
        }
        if (curSum >= n) {
            return;
        }
        for (int i = sl.length() > 0 ? sl.top() : 1; i <= n; i++) {
            sl.push(i);
            curSum += i;
            generate();
            curSum -= i;
            sl.pop();
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        this.out = out;
        generate();
    }
}
