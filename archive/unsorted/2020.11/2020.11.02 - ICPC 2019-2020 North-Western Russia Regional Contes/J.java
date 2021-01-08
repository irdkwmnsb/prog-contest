package main;

import java.io.PrintWriter;

public class J {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                in.hasNextInt();
                a[i][j] = in.nextChar() - '0';
            }
        }
        boolean[][] matx = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    matx[i][j] = true;
//                    a[i][j] = 0;
                    for (int k = j; k < n; k++) {
                        a[i][k] = (a[i][k] - a[j][k] + 10) % 10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(matx[i][j] ? 1 : 0);
            }
            out.println();
        }
    }
}
