package main;

import main.Scanner;
import main.randoms.Random2;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class B {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt();
        int[] a = new int[n];
        a[0] = in.nextInt();
        Random2 arand = new Random2(23, 21563, 0, a[0], 16714589);
        for (int i = 1; i < n; i++) {
            a[i] = arand.get();
        }
        IntSparseTable st = new IntSparseTable(a, Integer::min);
        int u = in.nextInt(), v = in.nextInt();
        Random2 urand = new Random2(17, 751, 1, u, n);
        Random2 vrand = new Random2(13, 593, 1, v, n);
        int ans = st.getInterval(u - 1, v - 1);
//        out.println(Arrays.toString(a));
//        out.println(String.format("%d: %d %d %d", 1, u, v, ans));
        for (int i = 1; i < m; i++) {
            u = urand.get(ans + 2 * i);
            v = vrand.get(ans + 5 * i);
            ans = st.getInterval(u - 1, v - 1);
//            out.println(String.format("%d: %d %d %d", i, u, v, ans));
        }
        out.println(String.format("%d %d %d", u, v, ans));
    }
}
