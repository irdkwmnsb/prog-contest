package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class N {
    static class Comparator {
        final int a, b;

        Comparator(int a, int b) {
            this.a = a;
            this.b = b;
        }

        boolean hasNode(int node) {
            return a == node || b == node;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] ans = new int[][]{{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                {1, 4, 2, 3, 5, 8, 6, 7, 9, 12, 10, 11, 13, 16, 14, 15},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                {1, 8, 2, 7, 3, 6, 4, 5, 9, 16, 10, 15, 11, 14, 12, 13},
                {1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},
                {1, 16, 2, 15, 3, 14, 4, 13, 5, 12, 6, 11, 7, 10, 8, 9},
                {1, 5, 2, 6, 3, 7, 4, 8, 9, 13, 10, 14, 11, 15, 12, 16},
                {1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}};
        int c = 0;
        for (int[] an : ans) {
            for (int j = 0; j < an.length; j += 2) {
                if (an[j] <= n && an[j + 1] <= n) {
                    c++;
                }
            }
        }
        out.printf("%d %d %d\n", n, c, ans.length);
        for (int[] an : ans) {
            c = 0;
            for (int j = 0; j < an.length; j += 2) {
                if (an[j] <= n && an[j + 1] <= n) {
                    c++;
                }
            }
            out.print(c + " ");
            for (int j = 0; j < an.length; j += 2) {
                if (an[j] <= n && an[j + 1] <= n) {
                    out.print(an[j] + " " + an[j + 1] + " ");
                }
            }
            out.println();
        }
    }
}
