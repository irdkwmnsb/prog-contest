package main.producing;

import main.MathContext;

import java.util.stream.IntStream;

public abstract class Polynomial {
    static long MODULO = MathContext.MODULO;

    public abstract long getImpl(int n);
    public long get(int n) {
//        if(n <= 100) {
            return getImpl(n);
//        } else {
//            throw new RuntimeException("This should not execute");
//        }
    }

    public abstract Integer degree();

    public Polynomial add(Polynomial other) {
        return new AddPolynomial(this, other);
    }

    public Polynomial negate() {
        return new NegatePolynomial(this);
    }

    public Polynomial mul(Polynomial other) {
        return new MulPolynomial(this, other);
    }

    public Polynomial div(Polynomial other) {
        return new DivPolynomial(this, other);
    }

    public Polynomial substitute(Polynomial other, int accuracy) {
        return IntStream
                .range(0, accuracy + 1)
                .mapToObj((i) -> SimplePolynomial.of(this.get(i)).mul(other.pow(i)))
                .reduce(SimplePolynomial.ZERO, Polynomial::add);
    }

    public Polynomial pow(long exp) {
        Polynomial ans = SimplePolynomial.ONE;
        Polynomial t = this;
        for(long n = exp; n > 0; n >>= 1) {
            if((n & 1) == 1) {
                ans = ans.mul(t);
            }
            t = t.mul(t);
        }
        return ans;
    }

    abstract public String explain();
}

