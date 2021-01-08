package main;

import jdk.jfr.Unsigned;
import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.LongStream;

public class J {
    static class JRandom {
        @Unsigned
        private int cur = 0;
        @Unsigned
        private final int a, b;

        JRandom(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int nextRand24() {
            cur = cur * a + b;
            return cur >>> 8;
        }

        public int nextRand32() {
            int a = nextRand24();
            int b = nextRand24();
            return (a << 8) ^ b;
        }
    }

    long k;
    long[] buf;

    private long mergeSortGetInv(long[] a, int left, int right) {
        if (left + 1 >= right) {
            return 0;
        }
        int m = (right + left) / 2;
        long inv = mergeSortGetInv(a, left, m) + mergeSortGetInv(a, m, right);

        int l = left, r = m;
        while (l < m && r < right) {
            if (a[l] + k > a[r]) {
                inv += m - l;
                r++;
            } else {
                l++;
            }
        }
        l = left;
        r = m;
        int i = 0;
        while (l < m && r < right) {
            if (a[l] < a[r]) {
                buf[i++] = a[l++];
            } else {
                buf[i++] = a[r++];
            }
        }
        while (l < m) {
            buf[i++] = a[l++];
        }
        while (r < right) {
            buf[i++] = a[r++];
        }
        System.arraycopy(buf, 0, a, left, right - left);
        return inv;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        k = in.nextLong();
        JRandom random = new JRandom(in.nextInt(), in.nextInt());
        long[] a = new long[n + 1];
        buf = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = random.nextRand32();
            a[i] = a[i - 1] + a[i];
        }
        long invs = mergeSortGetInv(a, 0, n + 1);
        System.err.println(invs);
        out.println((long) n * (n + 1) / 2 - invs);
    }
}
