package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;

public class E {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = -1;
            r[i] = -1;
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        int[] sortedIndices = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            sortedIndices[i] = i;
            p[i] = -1;
        }
        Sorts.sort(sortedIndices, ((a, b) -> Integer.compare(x[a], x[b])));
        for (int i = 1; i < n; i++) {
            int mostRight = i - 1;
            int toHang = -1;
            while (mostRight != -1 && y[sortedIndices[i]] < y[sortedIndices[mostRight]]) {
                toHang = mostRight;
                mostRight = p[mostRight];
            }
            if (toHang != -1) {
                l[i] = toHang;
                p[toHang] = i;
            }
            p[i] = mostRight;
            if (mostRight != -1) {
                r[mostRight] = i;
            }

        }
        int[] revInd = new int[n];
        for (int i = 0; i < n; i++) {
            revInd[sortedIndices[i]] = i;
        }
//        int root = -1;
//        for (int i = 0; i < n; i++) {
//            if (p[i] == i) {
//                root = i;
//            }
//            if (l[i] != -1) {
//                p[l[i]] = i;
//            }
//            if (r[i] != -1) {
//                p[r[i]] = i;
//            }
//        }
//        p[root] = -1;
        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.print(getOldInd(i, p, sortedIndices, revInd) + 1);
            out.print(" ");
            out.print(getOldInd(i, l, sortedIndices, revInd) + 1);
            out.print(" ");
            out.print(getOldInd(i, r, sortedIndices, revInd) + 1);
            out.print("\n");
        }
    }

    int getOldInd(int ind, int[] a, int[] rev, int[] sorted) {
        if (ind == -1 || a[sorted[ind]] == -1) {
            return -1;
        } else {
            return rev[a[sorted[ind]]];
        }
    }
}
