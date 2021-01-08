package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class permutations {
    ArrayList<Integer> curAns = new ArrayList<>();
    boolean[] used;
    int n;
    PrintWriter out;

    void generate() {
        if (curAns.size() == n) {
            for (int a : curAns) {
                out.print(a + 1);
                out.print(" ");
            }
            out.println();
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                used[i] = true;
                curAns.add(i);
                generate();
                curAns.remove(curAns.size() - 1);
                used[i] = false;
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        n = in.nextInt();
        used = new boolean[n];
        this.out = out;
        generate();
    }
}
