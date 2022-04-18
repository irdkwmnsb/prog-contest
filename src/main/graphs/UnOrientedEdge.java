package main.graphs;

import java.util.Objects;

public class UnOrientedEdge extends Edge {
    public UnOrientedEdge(int from, int to) {
        super(from, to);
    }

    @Override
    public UnOrientedEdge clone() throws CloneNotSupportedException {
        return (UnOrientedEdge) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnOrientedEdge)) return false;
        Edge edge = (Edge) o;
        return (getFrom() == edge.getFrom() && getTo() == edge.getTo()) ||
                (getTo() == edge.getFrom() && getFrom() == edge.getTo());
    }

    @Override
    public int hashCode() {
        return getFrom() * getTo();
    }
}
