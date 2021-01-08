package main;

import jdk.jfr.Unsigned;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class G {
    static class GRandom {
        @Unsigned
        private int cur = 0;
        @Unsigned
        private final int a, b;

        GRandom(int a, int b) {
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

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt(), n = in.nextInt();
        int[] a = new int[n];
        GRandom random = new GRandom(in.nextInt(), in.nextInt());
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = random.nextRand32();
            }
            Sorts.radixSortUnsigned(a);
            long ans = 0;
            long jj = 0;
            for (int j = 0; j < n; j++, jj++) {
                ans += Integer.toUnsignedLong(a[j]) * (jj + 1);
            }
            out.println(Long.toUnsignedString(ans));
        }
    }
}
