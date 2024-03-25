package main.graphs;

import main.Scanner;
import main.producing.Polynomial;
import main.producing.SimplePolynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UnOrientedEdgeListGraph extends AbstractGraph<UnOrientedEdge> {
    List<UnOrientedEdge> edges = new ArrayList<>();
    int n = 0;

    UnOrientedEdgeListGraph(UnOrientedEdgeListGraph g) {
        try {
            for (UnOrientedEdge e : g.edges) {
                edges.add(e.clone());
            }
        } catch (CloneNotSupportedException cloneNotSupportedException) {
            cloneNotSupportedException.printStackTrace();
        }
        n = g.n;
    }

    public UnOrientedEdgeListGraph(int n) {
        this.n = n;
    }

    @Override
    public List<UnOrientedEdge> getChildren(int v) {
        return edges.stream()
                .filter((edge) -> edge.getFrom() == v || edge.getTo() == v)
                .collect(Collectors.toList());
    }

    @Override
    public void addEdge(UnOrientedEdge edge) {
        n = Math.max(n, Math.max(edge.getFrom(), edge.getTo()) + 1);
        edges.add(edge);
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return edges.size();
    }

    @Override
    public boolean getOriented() {
        return false;
    }

    @Override
    public UnOrientedEdge getEdge(int v, int u) {
        UnOrientedEdge lookupEdge = new UnOrientedEdge(v, u);
        Optional<UnOrientedEdge> e = edges.stream()
                .filter((edge) -> edge.equals(lookupEdge))
                .findAny();
        return e.orElse(null);
    }

    public void removeEdge(Edge e) {
        edges.remove(e);
    }

    private static int shouldSub(int v, int toDelete) {
        return v > toDelete ? 1 : 0;
    }

    public void mergeEdge(Edge e) {
        removeEdge(e);
        int v = e.getFrom();
        int u = e.getTo();
        edges = edges.stream().map((edge) -> {
            if (u == edge.getFrom()) {
                return new UnOrientedEdge(v - shouldSub(v, u), edge.getTo() - shouldSub(edge.getTo(), u));
            }
            if (u == edge.getTo()) {
                return new UnOrientedEdge(v - shouldSub(v, u), edge.getFrom() - shouldSub(edge.getFrom(), u));
            }
            return new UnOrientedEdge(edge.getFrom() - shouldSub(edge.getFrom(), u),
                    edge.getTo() - shouldSub(edge.getTo(), u));
        }).distinct().collect(Collectors.toList());
        n -= 1;
    }

    public Polynomial getCharacteristicPolynomial() {
        if (getM() == 0) {
            long[] coeffs = new long[n + 1];
            coeffs[n] = 1;
            return new SimplePolynomial(coeffs);
        }
        Edge e = edges.get(edges.size() - 1);
        UnOrientedEdgeListGraph g = new UnOrientedEdgeListGraph(this);
        removeEdge(e);
        g.mergeEdge(e);
        return getCharacteristicPolynomial().add(g.getCharacteristicPolynomial().negate());
    }

    public static UnOrientedEdgeListGraph readFromEdgeList(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        UnOrientedEdgeListGraph g = new UnOrientedEdgeListGraph(n);
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1, u = in.nextInt() - 1;
            g.addEdge(new UnOrientedEdge(v, u));
        }
        return g;
    }

}
