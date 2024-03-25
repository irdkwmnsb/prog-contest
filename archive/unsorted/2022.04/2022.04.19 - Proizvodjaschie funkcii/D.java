package main;

import main.Scanner;
import main.producing.Polynomial;
import main.producing.SimplePolynomial;

import java.io.PrintWriter;

public class D {
    PrintWriter out;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        long r = in.nextLong();
        int k = in.nextInt();
        long[] p = in.readLongArray(k + 1);
        Polynomial ans = SimplePolynomial.ZERO;
        for(int i = 0; i<=k; i++) {
            Polynomial t = SimplePolynomial.of(p[i] * MathUtils.binpow(r, k-i));
            for(int j = 1; j<=k; j++) {
                t = t.mul(SimplePolynomial.of(j-i, 1));
            }
            ans = ans.add(t);
        }
        long f = Utils.factorial(k);
        long pw = MathUtils.binpow(r, k);
        long l = f * pw;
        for(int i = 0; i <= k; i++) {
            long ai = ans.get(i);
            long common = MathUtils.gcdex(Math.abs(ai), l).gcd();
            out.print(String.format("%s/%s ", ai / common, l / common));
        }
    }
}
