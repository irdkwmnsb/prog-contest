package main;

import main.Scanner;
import main.producing.Polynomial;
import main.producing.SimplePolynomial;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class H {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int k = in.nextInt();
        int n = in.nextInt();
        long[][] c = Stream.generate(() -> new long[k + 1]).limit(k + 1).toArray(long[][]::new);
        for (int i = 0; i <= k; i++) {
            c[i][0] = 1;
            c[i][i] = 1;
            for (int j = 1; j < i; j++) {
                c[i][j] = MathUtils.Modular.add(c[i - 1][j - 1], c[i - 1][j]);
            }
        }
        long[] lc = new long[k + 1];
        for (int i = 0; 2 * i < k - 1; i++) {
            lc[i] = c[k - i - 2][i];
            if (i % 2 == 1) {
                lc[i] = -lc[i];
            }
        }
        long[] rc = new long[k + 1];
        for (int i = 0; 2 * i < k; i++) {
            rc[i] = c[k - i - 1][i];
            if (i % 2 == 1) {
                rc[i] = -rc[i];
            }
        }
        Polynomial l = new SimplePolynomial(lc);
        Polynomial r = new SimplePolynomial(rc);
        long[] tc = new long[n + 1];
        tc[0] = 1 / r.get(0);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                tc[i] = MathUtils.Modular.sub(tc[i], MathUtils.Modular.mul(tc[j], r.get(i - j)));
            }
            tc[i] /= r.get(0);
        }
        Polynomial t = new SimplePolynomial(tc);
        out.print(IntStream
                .range(0, n)
                .mapToObj((i) ->
                        Long.toString(
                                IntStream
                                        .range(0, i + 1)
                                        .mapToLong((j) -> MathUtils.Modular.mul(l.get(j), t.get(i - j)))
                                        .reduce(0, MathUtils.Modular::add))

                )
                .collect(Collectors.joining("\n")));
    }
}
