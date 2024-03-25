package main.producing;

public class NegatePolynomial extends Polynomial {
    Polynomial backing;
    NegatePolynomial(Polynomial polynomial) {
        this.backing = polynomial;
    }

    @Override
    public long getImpl(int n) {
        return -backing.get(n);
    }

    @Override
    public Integer degree() {
        return backing.degree();
    }
    @Override
    public String explain() {
        return String.format("-(%s)", this.backing.explain());
    }
}
