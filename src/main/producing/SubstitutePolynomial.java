package main.producing;

import jdk.jshell.spi.ExecutionControl;
import main.MathContext;
import main.MathUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class SubstitutePolynomial extends Polynomial {
    long c[];
    final int accuracy;

    protected SubstitutePolynomial(Polynomial p, Polynomial t, int accuracy) {
        this.accuracy = accuracy;
        this.c = new long[accuracy + 1];
        for (int i = 0; i<=accuracy; i++) {
            for(int j = 0; j<=accuracy; j++) {
//                c[i + ]
            }
        }
    }

    @Override
    public long getImpl(int n) {
        return c[n];
    }

    @Override
    public Integer degree() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String explain() {
        return new SimplePolynomial(c).explain();
    }
}
