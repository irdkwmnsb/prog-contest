package main.producing;

public class AddPolynomial extends SimplePolynomial {
    Polynomial first;
    Polynomial second;

    protected AddPolynomial(Polynomial first, Polynomial second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public long getImpl(int n) {
        return (first.get(n) + second.get(n)) % MODULO;
    }
    @Override
    public Integer degree() {
        return Math.max(first.degree(), second.degree());
    }

    @Override
    public String explain() {
        return String.format("(%s) + (%s)", first.explain(), second.explain());
    }
}
