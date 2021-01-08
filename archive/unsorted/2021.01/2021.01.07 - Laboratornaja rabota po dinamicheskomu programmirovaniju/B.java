package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

public class B {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] d = IntStream.generate(() -> Integer.MAX_VALUE).limit(n + 2).toArray();
        d[0] = Integer.MIN_VALUE;
        int[] ind = IntStream.generate(() -> -1).limit(n + 1).toArray();
        int[] p = IntStream.generate(() -> -1).limit(n + 1).toArray();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(d, a[i]);
            if (j < 0) {
                j = -j - 1;
            }
            if (d[j - 1] < a[i] && a[i] < d[j]) {
                d[j] = a[i];
                p[i] = ind[j - 1];
                ind[j] = i;
                maxLen = Math.max(maxLen, j);
            }
        }

        IntList ans = new IntList();
        while (ind[maxLen] != -1) {
            ans.push(a[ind[maxLen]]);
            ind[maxLen] = p[ind[maxLen]];
        }
//        for (int i = 1; d[i] != Integer.MAX_VALUE; i++) {
//            ans.push(d[i]);
//        }
        out.println(ans.length());
        for (int i = ans.length() - 1; i >= 0; i--) {
            out.print(ans.get(i));
            out.print(" ");
        }
    }
}
