package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class choose {
    ArrayList<Integer> curAns = new ArrayList<>();
    int n;
    int k;
    PrintWriter out;

    void generate(int c) {
        if (curAns.size() == k) {
            for (int a : curAns) {
                out.print(a + 1);
                out.print(" ");
            }
            out.println();
        }
        for (int i = c; i < n; i++) {
            curAns.add(i);
            generate(i + 1);
            curAns.remove(curAns.size() - 1);
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        this.out = out;
        generate(0);
    }
}
