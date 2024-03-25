package main.producing;

import main.IntList;

public class PowPolynomial extends Polynomial {
    int a;
    int pow;
    public PowPolynomial(int a, int pow) {
        this.a = a;
        this.pow = pow;
    }

    @Override
    public long getImpl(int n) {
        if(n == pow)
            return a;
        else
            return 0;
    }

    @Override
    public Integer degree() {
        return pow;
    }

    @Override
    public String explain() {
        return String.format("%s * t ** %s", a, pow);
    }
}
