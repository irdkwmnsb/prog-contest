package main;

import main.Scanner;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class G {
    PrintWriter out;
    static MathContext MATH_CTX = new MathContext(1000, RoundingMode.HALF_UP);
    static BigDecimal ZERO = new BigDecimal(0, MATH_CTX);
    static BigDecimal ONE = new BigDecimal(1, MATH_CTX);
    static BigDecimal TWO = new BigDecimal(2, MATH_CTX);

    String getCodeByBounds(BigDecimal l, BigDecimal r) {
        if (l.equals(ZERO)) {
            return "0";
        }
        BigDecimal p = ONE;
        int q = 1;
//        BigDecimal b = TWO;

        while (true) {
            l = l.multiply(TWO);
            r = r.multiply(TWO);
            if (l.compareTo(p) > 0) {
                p = p.multiply(TWO).add(ONE);
            } else if (r.compareTo(p) <= 0) {
                p = p.multiply(TWO).subtract(ONE);
            } else {
                StringBuilder code = new StringBuilder();
                BigInteger e = p.toBigInteger();
                for (int i = 0; i < q; i++) {
                    code.insert(0, (e.getLowestSetBit() == 0 ? '1' : '0'));
                    e = e.shiftRight(1);
                }
                if (!e.equals(BigInteger.ZERO)) {
                    throw new RuntimeException("fuck");
                }
                return code.toString();
            }
            q++;
//            b = b.multiply(TWO);
        }
    }

    static class Segment {
        BigDecimal left;
        BigDecimal right;

        public Segment(BigDecimal left, BigDecimal right) {
            this.left = left;
            this.right = right;
        }
    }

    int[] calcAppearance(String s, int m) {
        int[] appearance = new int[m];
        for (int i = 0; i < s.length(); i++) {
            appearance[s.charAt(i) - 'a']++;
        }
        return appearance;
    }

    Segment[] calcSegments(String s, int m) {
        int[] appearance = calcAppearance(s, m);
        Segment[] segment = new Segment[m];
        BigDecimal slen = new BigDecimal(IntStream.of(appearance).sum(), MATH_CTX);
        BigDecimal l = BigDecimal.ZERO;
        for (int i = 0; i < m; i++) {
            segment[i] = new Segment(l,
                    l.add(new BigDecimal(appearance[i], MATH_CTX).divide(slen, MATH_CTX)));
            l = segment[i].right;
        }
        return segment;
    }

    BigDecimal[] arithmeticCoding(String s, int m) {
        Segment[] segment = calcSegments(s, m);
        BigDecimal left = BigDecimal.ZERO;
        BigDecimal right = BigDecimal.ONE;
        for (int i = 0; i < s.length(); i++) {
            int symb = s.charAt(i) - 'a';
            BigDecimal rml = right.subtract(left);
            BigDecimal newRight = left.add(rml.multiply(segment[symb].right));
            BigDecimal newLeft = left.add(rml.multiply(segment[symb].left));
            left = newLeft;
            right = newRight;
        }
        return new BigDecimal[]{left, right};
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int m = in.nextInt();
        String s = in.nextWord();
        out.println(m);
        for (int app : calcAppearance(s, m)) {
            out.print(app);
            out.print(" ");
        }
        out.println();
        BigDecimal[] range = arithmeticCoding(s, m);
//        System.err.println(range[0]);
//        System.err.println(range[1]);
        out.println(getCodeByBounds(range[0], range[1]));
    }
}
