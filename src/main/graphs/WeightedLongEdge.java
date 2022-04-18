package main.graphs;

import java.util.Comparator;

public class WeightedLongEdge extends Edge {
    protected final long w;

    public static Comparator<WeightedLongEdge> weightComparator = Comparator.comparing(edge -> edge.w);

    public WeightedLongEdge(int from, int to, long w) {
        super(from, to);
        this.w = w;
    }

    @Override
    public String toString() {
        return "WeightedLongEdge{" +
                "from=" + from +
                ", to=" + to +
                ", w=" + w +
                '}';
    }

    public long getW() {
        return w;
    }
}
