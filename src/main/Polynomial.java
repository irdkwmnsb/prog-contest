package main;

public class Polynomial {
    private final int[] c;

    public Polynomial(int... coeff) {
        c = coeff;
    }

    public int size() {
        return c.length - 1;
    }

    public int get(int i) {
        return c[i];
    }

    static Polynomial multiply(Polynomial a, Polynomial b) {
        int[] new_coeff = new int[a.c.length * b.c.length];
        for (int i = 0; i < a.c.length; i++) {
            for (int j = 0; j < b.c.length; j++) {
                new_coeff[i * j] += a.c[i] * b.c[i];
            }
        }
        return new Polynomial(new_coeff);
    }

    public static Polynomial subtract(Polynomial a, Polynomial b) {
        int n = Math.max(a.c.length, b.c.length);
        int[] new_coeff = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < a.c.length) {
                new_coeff[i] = a.c[i];
            }
            if (i < b.c.length) {
                new_coeff[i] -= b.c[i];
            }
        }
        return new Polynomial(new_coeff);
    }
}
