package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class A {
    static final class Segment {
        private final int li;
        private final int ri;
        private final double div;

        Segment(int li, int ri, double div) {
            this.li = li;
            this.ri = ri;
            this.div = div;
        }

        public int li() {
            return li;
        }

        public int ri() {
            return ri;
        }

        public double div() {
            return div;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Segment) obj;
            return this.li == that.li &&
                    this.ri == that.ri &&
                    Double.doubleToLongBits(this.div) == Double.doubleToLongBits(that.div);
        }

        @Override
        public int hashCode() {
            return Objects.hash(li, ri, div);
        }

        @Override
        public String toString() {
            return "Segment[" +
                    "li=" + li + ", " +
                    "ri=" + ri + ", " +
                    "div=" + div + ']';
        }

    }
    PrintWriter out;
    List<Segment> segments = new ArrayList<>();
    List<Double> p = new ArrayList<>();
    double m;
    int n;
    Random random = new Random();
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt();
        int N = n;
        int q = in.nextInt();


        int[] f = in.readIntArray(n);

        int F = IntStream.of(f).sum();

        p = IntStream.of(f).mapToDouble((a) -> a * 1.).map((x) -> x / (F * 1.0)).boxed().collect(Collectors.toList());

        m = p.stream().collect(Collectors.averagingDouble(Double::doubleValue));
        while(true) {
            fillEquals();
            if (n == 0) {
                break;
            }
            int smallest = find(lessPred);
            int biggset = find(gtPred);
            segments.add(new Segment(smallest, biggset, p.get(smallest)));
            p.set(biggset, p.get(biggset) - m + p.get(smallest));
            p.set(smallest, -1.0);
            n--;
        }

        System.err.println(segments);

        for (int i = 0; i < q; i++) {
            out.print(getRandomInt(N) + 1);
            out.print(" ");
        }
        out.println();
    }
    double EPS = 0.00001;
    IntPredicate equalsPred = (i) -> Math.abs(p.get(i) - m) < EPS;
    IntPredicate lessPred = (i) -> p.get(i) != -1 && p.get(i) < m;
    IntPredicate gtPred = (i) -> p.get(i) != -1 && p.get(i) > m;
    int find(IntPredicate pred) {
        return IntStream.range(0, p.size()).filter(pred).findFirst().orElse(-1);
    }

    int getRandomInt(int n) {
        Segment segment = segments.get((int) Math.floor(random.nextDouble() * n));

        if (segment.ri() == -1) {
            return segment.li();
        } else {
            if (random.nextDouble() * m > segment.div()) {
                return segment.ri();
            } else {
                return segment.li();
            }
        }
    }

    void fillEquals() {
        while (true) {
            int ind = find(equalsPred);
            if (ind == -1) {
                break;
            }
            p.set(ind, -1.0);
            segments.add(new Segment(ind, -1, -1));
            n--;
        }
    }
}
