package main.producing;

import main.MathUtils;

import java.util.stream.IntStream;

public class DivPolynomial extends SimplePolynomial {
    final Polynomial first;
    final Polynomial second;

    protected DivPolynomial(Polynomial first, Polynomial second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public long getImpl(int n) {
        if (n < this.knownCoeffs.length()) {
            return this.knownCoeffs.get(n);
        } else {
            long cn = ((
                    (first.get(n) + MODULO - IntStream
                            .range(0, n)
                            .mapToLong((i) -> (this.get(i) * this.second.get(n - i)) % MODULO)
                            .reduce(0, (a, b) -> (a + b) % MODULO)
                    ) % MODULO) * MathUtils.getInverse(this.second.get(0))) % MODULO;
            this.knownCoeffs.push(cn);
            return cn;
        }
    }

    @Override
    public Integer degree() {
        return null;
    }

    @Override
    public String explain() {
        return String.format("(%s) / (%s)", first.explain(), second.explain());
    }
}
