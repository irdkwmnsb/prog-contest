package main.graphs;

import main.Scanner;
import main.Utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Stream.generate;

public class MatrixGraph<E extends Edge> extends AbstractGraph<E> {
    protected final Object[] g;
    protected final int n;
    protected int m = 0;
    protected boolean oriented;

    public MatrixGraph(int n) {
        this(n, false);
    }

    public MatrixGraph(int n, boolean oriented) {
        this.n = n;
        this.g = Stream.generate(() -> (E) null).limit((long) n * n).toArray();
        this.oriented = oriented;
    }


    private int getIndex(int v, int u) {
        return v * n + u;
    }

    @Override
    public List<E> getChildren(int v) {
        checkVertex(v);
        //noinspection unchecked
        return  IntStream.range(0, n).mapToObj((i) -> (E) g[getIndex(v, i)]).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void addEdge(E edge) {
        if (!(Utils.inRange(0, n - 1, edge.from) && Utils.inRange(0, n - 1, edge.to))) {
            throw new InputMismatchException("Invalid edge for graph of size=" + n + ": " + edge.toString());
        }
        g[getIndex(edge.from, edge.to)] = edge;
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

    @Override
    public boolean getOriented() {
        return oriented;
    }

    private void checkVertex(int v) {
        if (!(Utils.inRange(0, n - 1, v))) {
            throw new InputMismatchException("Graph does not have node with index " + v);
        }
    }

    @Override
    public E getEdge(int v, int u) {
        checkVertex(v);
        checkVertex(u);
        return (E) g[getIndex(v, u)];
    }

    public static MatrixGraph<WeightedLongEdge> readWeightedLongTree(Scanner in) {
        int n = in.nextInt();
        MatrixGraph<WeightedLongEdge> g = new MatrixGraph<>(n);
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            g.addEdge(new WeightedLongEdge(from, to, w));
            g.addEdge(new WeightedLongEdge(to, from, w));
        }
        return g;
    }

    public static MatrixGraph<WeightedLongEdge> readWeightedLongGraph(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        MatrixGraph<WeightedLongEdge> g = new MatrixGraph<>(n);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            g.addEdge(new WeightedLongEdge(from, to, w));
            g.addEdge(new WeightedLongEdge(to, from, w));
        }
        return g;
    }

    public static MatrixGraph<Edge> readOrientedCompleteEdgeListGraph(Scanner in) {
        int n = in.nextInt();
        MatrixGraph<Edge> g = new MatrixGraph<>(n);
        for (int i = 0; i < n * (n - 1) / 2; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            g.addEdge(new Edge(from, to));
        }
        return g;
    }

    public static MatrixGraph<Edge> readSymmetricGraph(Scanner in) {
        int n = in.nextInt();
        MatrixGraph<Edge> g = new MatrixGraph<>(n);
        for (int i = 1; i < n; i++) {
            String line = in.nextWord();
            for(int j = 0; j < i; j++) {
                if(line.charAt(j) == '1') {
                    g.addEdge(new Edge(i, j));
                    g.addEdge(new Edge(j, i));
                }
            }
        }
        return g;
    }
}
