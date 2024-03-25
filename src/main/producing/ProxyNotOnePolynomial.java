package main.producing;

public class ProxyNotOnePolynomial extends Polynomial {
    Polynomial backing;
    public ProxyNotOnePolynomial(Polynomial polynomial) {
        this.backing = polynomial;
    }

    @Override
    public long getImpl(int n) {
        if(n == 0)
            return 0;
        return backing.get(n);
    }

    @Override
    public Integer degree() {
        return backing.degree();
    }
    @Override
    public String explain() {
        return String.format("proxy(%s)", (this.backing.explain()));
    }
}
