package main.graphs;

public class FlowEdge extends Edge {
    FlowEdge rev;
    int c;
    int ind;
    public int f;

    public FlowEdge(int from, int to, int c, int ind) {
        super(from, to);
        this.ind = ind;
        this.c = c;
    }

    public FlowEdge makeReverse() {
        this.rev = new FlowEdge(this.to, this.from, this.c, this.ind);
        tangle(this.rev);
        return this.rev;
    }

    public void tangle(FlowEdge other) {
        this.rev = other;
        other.rev = this;
    }

    public FlowEdge getRev() {
        return rev;
    }

    public int getC() {
        return c;
    }

    public int getInd() {
        return ind;
    }

    @Override
    public String toString() {
        return "FlowEdge{" +
                "c=" + c +
                ", ind=" + ind +
                ", f=" + f +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
