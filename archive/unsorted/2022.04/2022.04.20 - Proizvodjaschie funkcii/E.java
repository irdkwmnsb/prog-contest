package main;

import main.Scanner;
import main.producing.Polynomial;
import main.producing.SimplePolynomial;

import java.io.PrintWriter;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class E {
    PrintWriter out;
    long[][] eulier = new long[][]{
            new long[]{1},
            new long[]{1},
            new long[]{1, 1},
            new long[]{1, 4, 1},
            new long[]{1, 11, 11, 1},
            new long[]{1, 26, 66, 26, 1},
            new long[]{1, 57, 302, 302, 57, 1},
            new long[]{1, 120, 1191, 2416, 1191, 120, 1},
            new long[]{1, 247, 4293, 15619, 15619, 4293, 247, 1},
            new long[]{1, 502, 14608, 88234, 156190, 88234, 14608, 502, 1},
            new long[]{1, 1013, 47840, 455192, 1310354, 1310354, 455192, 47840, 1013, 1},
    };

    public void print(Polynomial p) {
        out.println(p.degree());
        print(p, p.degree());
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
        int r = in.nextInt();
        int d = in.nextInt();
        int[] p = in.readIntArray(d + 1);

        Polynomial t = SimplePolynomial.of(1, -r);
        Polynomial f = SimplePolynomial.ZERO;
        Polynomial z = SimplePolynomial.ONE;

        for (int i = d; i >= 0; i--) {
            int finalI = i;
            Polynomial q = i == 0 ? SimplePolynomial.ONE :
                    new SimplePolynomial(LongStream.concat(
                            LongStream.of(0),
                            IntStream.range(0, i).mapToLong((j) -> eulier[finalI][j] * MathUtils.binpow(r, j))
                    ).toArray());
            f = f.add(q.mul(z).mul(SimplePolynomial.of(p[i] * (i == 0 ? 1L : r))));
            z = z.mul(t);
        }
        print(f);
        print(z);
    }
}
