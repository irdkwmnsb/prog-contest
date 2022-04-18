package main;

import main.Scanner;
import main.randoms.Random1;

import java.io.PrintWriter;
import java.util.Arrays;

public class A {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = 16777216;
        int[] s = new int[n + 1];
        int m = in.nextInt(), q = in.nextInt();
        int a = in.nextInt(), b = in.nextInt();
        Random1 rand = new Random1(a, b);
        for (int i = 0; i < m; i++) {
            int x = rand.nextRandInt(8);
            int l = rand.nextRandInt(8);
            int r = rand.nextRandInt(8);
            if (l > r) {
                int c = l;
                l = r;
                r = c;
            }
            s[l] += x;
            s[r + 1] -= x;
        }
        int cur_sum = s[0];
        for (int i = 1; i < n; i++) {
            cur_sum += s[i];
            s[i] = s[i - 1] + cur_sum;
        }
        int ans = 0;
        for (int i = 0; i < q; i++) {
            int l = rand.nextRandInt(8);
            int r = rand.nextRandInt(8);
            if (l > r) {
                int c = l;
                l = r;
                r = c;
            }
            ans += s[r];
            if (l > 0) {
                ans -= s[l - 1];
            }
        }
        out.println(Integer.toUnsignedString(ans));
    }
}
