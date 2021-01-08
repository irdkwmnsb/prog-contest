package main;

import main.Scanner;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class H {
    static MathContext MATH_CTX = new MathContext(1000, RoundingMode.HALF_UP);
    static BigDecimal ZERO = new BigDecimal(0, MATH_CTX);
    static BigDecimal ONE = new BigDecimal(1, MATH_CTX);
    static BigDecimal TWO = new BigDecimal(2, MATH_CTX);

    static class Segment {
        BigDecimal left;
        BigDecimal right;
        char c;

        public Segment(BigDecimal left, BigDecimal right, char c) {
            this.left = left;
            this.right = right;
            this.c = c;
        }
    }

    Segment[] defineSegments(int m, int[] appearance) {
        Segment[] segment = new Segment[m];
        BigDecimal l = ZERO;
        BigDecimal slen = new BigDecimal(IntStream.of(appearance).sum(), MATH_CTX);
        for (int i = 0; i < m; i++) {
            segment[i] = new Segment(l,
                    l.add(new BigDecimal(appearance[i], MATH_CTX).divide(slen, MATH_CTX)),
                    (char) ('a' + i));
            l = segment[i].right;
        }
        return segment;
    }

    String arithmeticDecoding(int m, int[] appearance, BigDecimal code) {
        Segment[] segment = defineSegments(m, appearance);
        StringBuilder s = new StringBuilder();
        int n = IntStream.of(appearance).sum();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (code.compareTo(segment[j].left) >= 0 && code.compareTo(segment[j].right) < 0) {
                    s.append(segment[j].c);
                    code = code.subtract(segment[j].left).divide(segment[j].right.subtract(segment[j].left), MATH_CTX);
                    break;
                }
            }
        }
        return s.toString();
    }

    BigDecimal parseDecimal(String s) {
        BigInteger p = BigInteger.ZERO;
        BigInteger q = BigInteger.ONE;
        for (int i = 0; i < s.length(); i++) {
            p = p.shiftLeft(1);
            if (s.charAt(i) == '1') {
                p = p.or(BigInteger.ONE);
            }
            q = q.shiftLeft(1);
        }
        return new BigDecimal(p, MATH_CTX).divide(new BigDecimal(q, MATH_CTX), MATH_CTX);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int m = in.nextInt();
        int[] appearance = in.readIntArray(m);
        BigDecimal code = parseDecimal(in.nextWord());
        out.println(arithmeticDecoding(m, appearance, code));
    }
}
