package main.randoms;

public class Random2 {
    final long a, b, c, mod;
    long cur;

    public Random2(int a, int b, int c, int start, int mod) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.mod = mod;
        this.cur = start;
    }

    public int get(int mixin) {
        cur = (a * cur + b + mixin) % mod + c;
        return (int) cur;
    }

    public int get() {
        return get(0);
    }
}
