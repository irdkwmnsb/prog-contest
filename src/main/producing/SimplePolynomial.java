package main.producing;

import main.IntList;
import main.LongList;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimplePolynomial extends Polynomial {
    public static SimplePolynomial ONE = SimplePolynomial.of(1);
    public static SimplePolynomial ZERO = SimplePolynomial.of(0);
    LongList knownCoeffs = new LongList();
    public SimplePolynomial(long[] coeffs) {
        knownCoeffs = new LongList(coeffs);
        while(knownCoeffs.length() > 0 && knownCoeffs.top() == 0) {
            knownCoeffs.pop();
        }
    }

    public static SimplePolynomial of(long... coeffs) {
        return new SimplePolynomial(coeffs);
    }

    SimplePolynomial(SimplePolynomial polynomial) {
        this.knownCoeffs = polynomial.knownCoeffs;
    }

    SimplePolynomial() {}

    @Override
    public long getImpl(int n) {
        if(n < knownCoeffs.length()) {
            return knownCoeffs.get(n);
        } else {
            return 0;
        }
    }

    @Override
    public Integer degree() {
        return this.knownCoeffs.length() - 1;
    }

    @Override
    public String explain() {
        return IntStream.range(0, degree() + 1)
                .filter((i) -> knownCoeffs.get(i) != 0 || i == 0)
                .mapToObj((i) -> i == 0 ? String.format("%s", knownCoeffs.get(i))
                        : String.format("%s * t ** %s", knownCoeffs.get(i), i))
                .collect(Collectors.joining(" + "));
    }
}
