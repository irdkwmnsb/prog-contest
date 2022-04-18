package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class C {
    PrintWriter out;
    Scanner in;

    void printPath(int[] path) {
        out.print("0 ");
        for (int j : path) {
            out.print(String.format("%d ", j + 1));
        }
        out.println();
        out.flush();
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        this.in = in;
        int n = in.nextInt();
        int[] path = new int[n];
        path[0] = 0;
        for (int i = 1; i < n; i++) {
            int l = -1, r = i;
            while (r - l > 1) {
                int m = (r + l) / 2;
                out.println(String.format("1 %d %d", path[m] + 1, i + 1));
                out.flush();
                if (in.nextWord().equals("YES")) {
                    l = m;
                } else {
                    r = m;
                }
            }
            System.arraycopy(path, r, path, r+1, i - r);
            path[r] = i;
        }
        printPath(path);
    }
}
