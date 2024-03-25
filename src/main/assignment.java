package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class assignment {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[][] a = Stream.generate(() -> in.readIntArray(n)).limit(n).toArray(int[][]::new);

        int[] rows = Utils.makeIntArray(n, -1);
        int[] cols = Utils.makeIntArray(n, -1);
        int[] remr = new int[n];
        int[] remc = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] usedcols = new boolean[n];
            int[] minc = Utils.makeIntArray(n, Integer.MAX_VALUE);
            int[] minp = new int[n];

            int row = i;
            int col = -1;
            do {
                int minj = -1;
                for (int j = 0; j < n; j++) {
                    int newMax = a[row][j] + remr[row] + remc[j];
                    if (!usedcols[j]) {
                        if (newMax < minc[j]) {
                            minc[j] = newMax;
                            minp[j] = row;
                        }
                        if (minj == -1 || minc[j] < minc[minj]) {
                            minj = j;
                        }
                    }
                }
                int minv = minc[minj];
                remr[i] -= minv;
                for (int j = 0; j < n; j++) {
                    if (!usedcols[j]) {
                        minc[j] -= minv;
                    } else {
                        remr[cols[j]] -= minv;
                        remc[j] += minv;
                    }
                }
                usedcols[minj] = true;
                if (cols[minj] == -1) {
                    row = minp[minj];
                    col = minj;
                } else {
                    row = cols[minj];
                }
            } while (col == -1);
            while (row != i) {
                int temp = rows[row];
                cols[col] = row;
                rows[row] = col;
                col = temp;
                row = minp[col];
            }
            cols[col] = row;
            rows[row] = col;
        }

        int ans = IntStream.range(0, n).map((i) -> a[i][rows[i]]).sum();
        out.println(ans);
        for (int i = 0; i < n; i++) {
            out.printf("%d %d%n", i + 1, rows[i] + 1);
        }
    }
}
