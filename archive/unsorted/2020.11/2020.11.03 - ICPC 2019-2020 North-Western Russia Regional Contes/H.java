package main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

public class H {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + a[i - 1];
        int max = Arrays.stream(a).max().orElseThrow();
        int allSum = sum[n];
        int[] anss = new int[allSum + 1];
        for (int t = 0; t <= allSum; t++) {
            if (t < max) {
                anss[t] = -1;
                continue;
            }
            int b = 0, ans = 0;
            while (b != n) {
                b = Utils.upperBound(sum, sum[b] + t) - 1;
                ans++;
            }
            anss[t] = ans;
        }
        for (int test : in.readIntArray(in.nextInt())) {
            int res = anss[test];
            if (res == -1) {
                out.println("Impossible");
            } else {
                out.println(res);
            }
        }
        out.close();
    }
}
