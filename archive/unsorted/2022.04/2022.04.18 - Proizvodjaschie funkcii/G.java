package main;

import main.Scanner;
import main.producing.Polynomial;
import main.producing.PowPolynomial;
import main.producing.ProxyNotOnePolynomial;
import main.producing.SimplePolynomial;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class G {
    int MAX_N = 6;
    PrintWriter out;
    Scanner in;
    public void print(Polynomial p) {
        out.println(p.degree());
        print(p, p.degree());
    }
    Map<String, Function<Polynomial[], Polynomial>> lexems = Map.of(
            "P", (polynomials -> polynomials[0].mul(polynomials[1])),
            "L", (polynomials) -> set(polynomials[0]),
            "S", (polynomials -> {
                Polynomial acc = SimplePolynomial.ONE;
                for (int n = 1; n <= MAX_N; n++) {
                    long npow = polynomials[0].get(n);
                    Polynomial pow = set(new PowPolynomial(1, n)).pow(npow);
                    acc = acc.mul(pow);
                }
                return acc;
            })
    );

    Map<String, Polynomial> consts = Map.of(
            "B", SimplePolynomial.of(0, 1)
    );

    Polynomial parse() {
        String c = in.next(this::token);
        if(c == null) {
            throw new RuntimeException("Unexpected EOF");
        }
        if(lexems.containsKey(c)) {
            in.nextChar();
            List<Polynomial> args = new ArrayList<>();
            char delim;
            do {
                args.add(parse());
                delim = in.nextChar();
            } while (delim != ')');
            return lexems.get(c).apply(args.toArray(Polynomial[]::new));
        } else if(consts.containsKey(c)) {
            return consts.get(c);
        } else {
            throw new RuntimeException(String.format("Did not expect %s", c));
        }
    }

    boolean token(char c) {
        return 'A' <= c && c <= 'Z';
    }

    private Polynomial set(Polynomial p) {
        return SimplePolynomial.ONE.div(SimplePolynomial.ONE.add(removeZeroth(p).negate()));
    }

    private Polynomial removeZeroth(Polynomial p) {
        return new ProxyNotOnePolynomial(p);
    }

    public void print(Polynomial p, int n) {
        for(int i = 0; i<=n; i++) {
            if(i != 0)
                out.print(" ");
            out.print(p.get(i));
        }
        out.println();
    }
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        this.in = in;
        Polynomial res = parse();
//        out.println(res.explain());
        print(res, MAX_N);
    }
}
