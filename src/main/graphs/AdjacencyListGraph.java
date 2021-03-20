package main.graphs;

import main.Scanner;
import main.Utils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class AdjacencyListGraph<E extends Edge> implements Graph<E> {
    private final List<E>[] g;
    private final int n;
    private int m = 0;

    public AdjacencyListGraph(int n) {
        this.n = n;
        this.g = Stream.generate(ArrayList<E>::new).limit(n).toArray((IntFunction<List<E>[]>) List[]::new);
    }

    @Override
    public List<E> getChildren(int v) {
        if (!(Utils.inRange(0, n, v))) {
            throw new InputMismatchException("Graph does not have node with index " + v);
        }
        return g[v];
    }

    @Override
    public void addEdge(E edge) {
        if (!(Utils.inRange(0, n, edge.from) && Utils.inRange(0, n, edge.to))) {
            throw new InputMismatchException("Invalid edge for graph of size=" + n + ": " + edge.toString());
        }
        g[edge.from].add(edge);
        m++;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    public static AdjacencyListGraph<WeightedLongEdge> readWeightedLongTree(Scanner in) {
        int n = in.nextInt();
        AdjacencyListGraph<WeightedLongEdge> g = new AdjacencyListGraph<>(n);
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            g.addEdge(new WeightedLongEdge(from, to, w));
            g.addEdge(new WeightedLongEdge(to, from, w));
        }
        return g;
    }

    public static AdjacencyListGraph<WeightedLongEdge> readWeightedLongGraph(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        AdjacencyListGraph<WeightedLongEdge> g = new AdjacencyListGraph<>(n);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            g.addEdge(new WeightedLongEdge(from, to, w));
            g.addEdge(new WeightedLongEdge(to, from, w));
        }
        return g;
    }
}
