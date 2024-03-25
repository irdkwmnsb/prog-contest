package main.producing;

import java.util.stream.LongStream;

public class RecurringPolynomial {
    SimplePolynomial p;
    SimplePolynomial q;
    public RecurringPolynomial(long[] a, long[] c) {
        q = new SimplePolynomial(LongStream.concat(LongStream.of(1), LongStream.of(c).map((i) -> -i)).toArray());
        int k = a.length;
        long[] ps = new long[k];
        for (int n = 0; n < k; n++) {
            for (int i = 0; i <= n; i++) {
                ps[n] += a[n - i] * q.get(i);
            }
        }
        p = new SimplePolynomial(ps);
    }

    public SimplePolynomial getP() {
        return this.p;
    }

    public SimplePolynomial getQ() {
        return this.q;
    }

    public Polynomial toPolynomial() {
        return new DivPolynomial(p, q);
    }
}
