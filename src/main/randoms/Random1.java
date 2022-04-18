package main.randoms;

import jdk.jfr.Unsigned;

public class Random1 {
    @Unsigned
    final int a;
    @Unsigned
    final int b;
    int cur = 0;

    public Random1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public final int nextRandInt(int n) {
        cur = cur * a + b;
        return cur >>> n;
    }
}
