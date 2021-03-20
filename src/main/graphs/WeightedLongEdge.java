package main.graphs;

public class WeightedLongEdge extends Edge {
    protected final long w;

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
