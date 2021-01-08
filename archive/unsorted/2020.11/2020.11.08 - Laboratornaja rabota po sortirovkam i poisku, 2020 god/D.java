package main;

import net.egork.chelper.checkers.TokenChecker;

import java.io.PrintWriter;
import java.util.function.DoubleSupplier;
import java.util.stream.DoubleStream;

class Garland {
    private final double first;
    private final double second;
    private final int n;
    private double[] heights = null;

    Garland(double first, double second, int n) {
        this.first = first;
        this.second = second;
        this.n = n;
    }

    private void calcHeights() {
        heights = new double[n];
        heights[0] = first;
        heights[1] = second;
        for (int i = 2; i < n; i++) {
            heights[i] = 2 * (heights[i - 1] + 1) - heights[i - 2];
        }
    }

    public double[] getHeights() {
        if (heights == null) {
            calcHeights();
        }
        return heights;
    }

    public double getLowest() {
        return DoubleStream.of(getHeights()).min().orElseThrow();
    }
}

public class D {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        double a = in.nextDouble();
        double b = Utils.lowerBound(0, a, (second) -> new Garland(a, second, n).getLowest() > 0).orElseThrow();
        out.println(new Garland(a, b, n).getHeights()[n - 1]);
    }
}
