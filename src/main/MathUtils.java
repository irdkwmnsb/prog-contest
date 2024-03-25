package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MathUtils {
    static long MODULO = MathContext.MODULO;

    public static final class GCDExtResult {
        private final long gcd;
        private final long x;
        private final long y;

        private GCDExtResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }

        public long gcd() {
            return gcd;
        }

        public long x() {
            return x;
        }

        public long y() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (GCDExtResult) obj;
            return this.gcd == that.gcd &&
                    this.x == that.x &&
                    this.y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gcd, x, y);
        }

        @Override
        public String toString() {
            return "GCDExtResult[" +
                    "gcd=" + gcd + ", " +
                    "x=" + x + ", " +
                    "y=" + y + ']';
        }
    }

    public static class Modular {
        public static long mul(long a, long b) {
            return Math.floorMod(a * b, MODULO);
        }
        public static long add(long a, long b) {
            return Math.floorMod(a + b, MODULO);
        }
        static long sub(long a, long b) {
            return Math.floorMod(a - b, MODULO);
        }
        static long div(long a, long b) {
            return Math.floorMod(a * getInverse(b), MODULO);
        }
    }

    static Map<Long, Long> inverse = new HashMap<>();

    public static long getInverse(long a) {
        return inverse.computeIfAbsent(a, (k) -> {
            GCDExtResult g = gcdex(k, MODULO);
            if (g.gcd != 1) {
                throw new RuntimeException("No inverse was found");
            } else {
                return (g.x % MODULO + MODULO) % MODULO;
            }
        });
    }

    public static GCDExtResult gcdex(long a, long b) {
        if (a == 0) {
            return new GCDExtResult(b, 0, 1);
        }
        GCDExtResult d = gcdex(b % a, a);
        //noinspection SuspiciousNameCombination
        return new GCDExtResult(d.gcd, d.y - (b / a) * d.x, d.x);
    }

    public static long binpow(long a, long b, long m) {
        long res = 1;
        while (b != 0)
            if ((b & 1) != 0) {
                res = (res * a) % m;
                --b;
            } else {
                a *= a;
                b >>= 1;
            }
        return res;
    }

    public static long binpow(long a, long b) {
        return binpow(a, b, MODULO);
    }
}
