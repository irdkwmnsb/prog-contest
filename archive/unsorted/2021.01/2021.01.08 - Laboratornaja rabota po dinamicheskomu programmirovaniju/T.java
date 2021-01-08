package main;

import main.Scanner;

import java.io.PrintWriter;

public class T {
    PrintWriter out;
    static long MOD = 999_999_937;

    long[][] multiply(long[][] a, long[][] b) {
        int l = a.length;
        int m = a[0].length;
        int n = b[0].length;
        long[][] c = new long[l][n];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return c;
    }

    long[][] makeE(int n) {
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++)
            res[i][i] = 1;
        return res;
    }

    long[][] binpow(long[][] a, long n) {
        long[][] res = makeE(a.length);
        while (n > 0) {
            if ((n & 1) > 0)
                res = multiply(res, a);
            a = multiply(a, a);
            n >>= 1;
        }
        return res;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        while (true) {
            long a = in.nextLong();
            if (a == 0)
                break;
            long[][] start = new long[][]{{3, 2}, {2, 1}};
            long[][] res = binpow(start, a);
            long ans = (res[0][0] + res[1][0]) % MOD;
            out.println(ans);
        }
    }
}
