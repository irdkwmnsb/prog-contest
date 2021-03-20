package main.graphs;

public class Edge {
    protected final int from;
    protected final int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
