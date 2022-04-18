package main.graphs;

import main.IntList;
import main.Scanner;
import main.Utils;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AdjacencyListGraph<E extends Edge> extends AbstractGraph<E> {
    protected final List<E>[] g;
    protected final int n;
    protected int m = 0;
    protected boolean oriented;

    public AdjacencyListGraph(int n) {
        this(n, false);
    }

    public AdjacencyListGraph(int n, boolean oriented) {
        this.n = n;
        this.g = Stream.generate(ArrayList<E>::new).limit(n).toArray((IntFunction<List<E>[]>) List[]::new);
        this.oriented = oriented;
    }


    public static AdjacencyListGraph<Edge> readTreeFromEdgeList(Scanner in) {
        int n = in.nextInt();
        AdjacencyListGraph<Edge> g = new AdjacencyListGraph<>(n);
        int m = n - 1;
        for (int i = 0; i < m; i++) {
            int v = in.nextInt() - 1, u = in.nextInt() - 1;
            g.addEdge(new Edge(v, u));
            g.addEdge(new Edge(u, v));
        }
        return g;
    }

    @Override
    public List<E> getChildren(int v) {
        checkVertex(v);
        return g[v];
    }

    @Override
    public void addEdge(E edge) {
        if (!(Utils.inRange(0, n - 1, edge.from) && Utils.inRange(0, n - 1, edge.to))) {
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
        for (E e : g[v]) {
            if (e.getTo() == u) {
                return e;
            }
        }
        return null;
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

    public static AdjacencyListGraph<WeightedLongEdge> readWeightedLongGraph(Scanner in, boolean oriented) {
        int n = in.nextInt();
        int m = in.nextInt();
        return readWeightedLongGraphNM(in, oriented, n, m);
    }

    public static AdjacencyListGraph<WeightedLongEdge> readWeightedLongGraphNM(Scanner in, boolean oriented, int n, int m) {
        AdjacencyListGraph<WeightedLongEdge> g = new AdjacencyListGraph<>(n, oriented);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            long w = in.nextLong();
            g.addEdge(new WeightedLongEdge(from, to, w));
            if(!oriented) {
                g.addEdge(new WeightedLongEdge(to, from, w));
            }
        }
        return g;
    }


    public static AdjacencyListGraph<Edge> readGraph(Scanner in, boolean oriented) {
        int n = in.nextInt();
        int m = in.nextInt();
        return readGraphNM(in, oriented, n, m);
    }

    public static AdjacencyListGraph<Edge> readGraphNM(Scanner in, boolean oriented, int n, int m) {
        AdjacencyListGraph<Edge> g = new AdjacencyListGraph<>(n, oriented);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1, to = in.nextInt() - 1;
            g.addEdge(new Edge(from, to));
            if(!oriented) {
                g.addEdge(new Edge(to, from));
            }
        }
        return g;
    }

    public static AdjacencyListGraph<Edge> readSymmetricGraph(Scanner in) {
        int n = in.nextInt();
        AdjacencyListGraph<Edge> g = new AdjacencyListGraph<>(n);
        for (int i = 1; i < n; i++) {
            String line = in.nextWord();
            for (int j = 0; j < i; j++) {
                if (line.charAt(j) == '1') {
                    g.addEdge(new Edge(i, j));
                    g.addEdge(new Edge(j, i));
                }
            }
        }
        return g;
    }

    public void sortAdjacencyList() {
        Comparator<Edge> comparator = Comparator.comparing(a -> -g[a.getTo()].size());
        for (int i = 0; i < n; i++) {
            g[i].sort(comparator);
        }
    }

    public static AdjacencyListGraph<Edge> readFromPruferCode(Scanner in) {
        int n = in.nextInt();
        AdjacencyListGraph<Edge> g = new AdjacencyListGraph<>(n, true);
        int[] prufer_code = IntStream.of(in.readIntArray(n - 2)).map((a) -> a-1).toArray();
        int[] degree = new int[n];
        for (int i = 0; i < n - 2; i++) {
            degree[prufer_code[i]]++;
        }

        TreeSet<Integer> leaves = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                leaves.add(i);
            }
        }

        for (int i = 0; i < n - 2; i++) {
            //noinspection ConstantConditions
            int u = leaves.pollFirst();

            int v = prufer_code[i];
            g.addEdge(new Edge(v, u));
            degree[v]--;
            if(degree[v] == 0) {
                leaves.add(v);
            }
        }
        g.addEdge(new Edge(leaves.first(), leaves.last()));
        return g;
    }
}
