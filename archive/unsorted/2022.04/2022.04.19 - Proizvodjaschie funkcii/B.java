package main;

import main.producing.Polynomial;
import main.producing.SimplePolynomial;

import static main.MathUtils.MODULO;
import static main.MathUtils.Modular.*;
import static main.MathUtils.getInverse;

import java.io.PrintWriter;

public class B {
    PrintWriter out;

    Polynomial getSqrt(Polynomial a, int accuracy) {
        Polynomial b = SimplePolynomial.ONE;
        long[] resCoeff = new long[accuracy];
        resCoeff[0] = 1;
        long quot = 1;
        long div = 1;
        for (int i = 1; i < accuracy; i++) {
            b = b.mul(a);
            div = mul(div, sub(1, 2L * (i - 1)));
            quot = mul(quot, mul(i, 2));
            long f = div(div, quot);
            for (int j = 0; j < accuracy; j++) {
                resCoeff[j] = add(resCoeff[j], mul(f, b.get(j)));
            }
        }
        return new SimplePolynomial(resCoeff);
    }

    Polynomial getExp(Polynomial a, int accuracy) {
        Polynomial b = SimplePolynomial.ONE;
        long[] resCoeff = new long[accuracy];
        resCoeff[0] = 1;
        long f = 1;
        for (int i = 1; i < accuracy; i++) {
            b = b.mul(a);
            f = mul(f, i);
            for (int j = 0; j < accuracy; j++) {
                resCoeff[j] = add(resCoeff[j], div(b.get(j), f));
            }
        }
        return new SimplePolynomial(resCoeff);
    }

    Polynomial getLog(Polynomial a, int accuracy) {
        Polynomial b = SimplePolynomial.ONE;
        long[] resCoeff = new long[accuracy];
        long f = -1;
        for (int i = 1; i < accuracy; i++) {
            b = b.mul(a);
            f = -f;
            for (int j = 0; j < accuracy; j++) {
                resCoeff[j] = add(resCoeff[j], mul(b.get(j), div(f, i)));
            }
        }
        return new SimplePolynomial(resCoeff);
    }

    public void print(Polynomial p, int n) {
        for (int i = 0; i < n; i++) {
            if (i != 0)
                out.print(" ");
            out.print(p.get(i));
        }
        out.println();
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int m = in.nextInt();
        Polynomial a = new SimplePolynomial(in.readLongArray(n + 1));
        Polynomial sqrt = getSqrt(a, m);
        print(sqrt, m);
        Polynomial exp = getExp(a, m);
        print(exp, m);
        Polynomial log = getLog(a, m);
        print(log, m);
    }
}
