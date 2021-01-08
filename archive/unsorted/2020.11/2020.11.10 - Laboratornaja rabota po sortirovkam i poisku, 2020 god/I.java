package main;



import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class I {
    int[] a, b, t;
    int m, n, p;

    public boolean possible(long time) {
        long[] possib = new long[m];
        for (int i = 0; i < m; i++) {
            if (a[i] == 0)
                if ((b[i] + t[i]) <= time)
                    possib[i] = (long) 1e5;
                else
                    possib[i] = 0;
            else
                possib[i] = Long.max(0, (time - t[i] - b[i]) / a[i]);
        }
        int[] indeces = IntStream.range(0, m).toArray();
        Sorts.sort(indeces, (i, j) -> -Long.compare(possib[i], possib[j]));
        long cakesLeft = p;
        for (int i = 0; i < Integer.min(n, m); i++) {
            cakesLeft -= possib[indeces[i]];
        }
        return cakesLeft <= 0;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        m = in.nextInt();
        a = new int[m];
        b = new int[m];
        t = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
            t[i] = in.nextInt();
        }
        n = in.nextInt();
        p = in.nextInt();
        long ans = Utils.lowerBound(-1L, (long) 1e15, this::possible).orElseThrow();
        out.println(ans);
    }
}
