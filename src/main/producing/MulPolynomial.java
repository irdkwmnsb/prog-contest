package main.producing;

import java.util.HashMap;
import java.util.Map;

public class MulPolynomial extends Polynomial {
    Polynomial first;
    Polynomial second;
    Map<Integer, Long> cache = new HashMap<>();

    MulPolynomial(Polynomial first, Polynomial second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public long getImpl(int n) {
        return cache.computeIfAbsent(n,
                (nn) -> {
                    long acc = 0;
                    int bound = nn + 1;
                    for (int i = 0; i < bound; i++) {
                        long i1 = ((first.getImpl(i) * second.getImpl(nn - i)) % MODULO);
                        acc = (acc + i1) % MODULO;
                    }
                    return acc;
                }
        );
    }
    @Override
    public Integer degree() {
        return first.degree() + second.degree();
    }
    @Override
    public String explain() {
        return String.format("(%s) * (%s)", first.explain(), second.explain());
    }
}
