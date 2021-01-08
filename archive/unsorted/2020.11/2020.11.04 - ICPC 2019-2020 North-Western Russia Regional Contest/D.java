package main;

import main.Scanner;

import java.io.PrintWriter;

public class D {
    static long mod = 998244353;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] pows = new long[n / 2 + 2];
        pows[0] = 1;
        for (int i = 1; i <= n / 2 + 1; i++) {
            pows[i] = (pows[i - 1] * k) % mod;
        }

        long[] r = new long[n + 1];
        for (int l = 1; l <= n; l++) {
            if (l % 2 == 0) {
                r[l] = (l / 2L) * (pows[l / 2] + pows[l / 2 + 1]) % mod;
            } else {
                r[l] = l * pows[(l + 1) / 2] % mod;
            }
        }
        for (int nn = 1; nn <= n; nn++) {
            for (IntList.IntListIterator it = Utils.divs(nn).iterator(); it.hasNext(); ) {
                int l = it.next();
                if (l < nn) {
                    r[nn] = (r[nn] - (nn / l * r[l] % mod) + mod) % mod;
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                ans = (ans + r[i]) % mod;
            }
        }
        out.println(ans);
        out.close();
    }
}
