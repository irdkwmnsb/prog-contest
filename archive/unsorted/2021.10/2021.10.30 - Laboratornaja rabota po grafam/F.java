package main;

import main.Scanner;
import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class F {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        AdjacencyListGraph<Edge> g = AdjacencyListGraph.readFromPruferCode(in);
        for (int v = 0; v < g.getN(); v++) {
            for (Edge e : g.getChildren(v)) {
                out.printf("%d %d\n", e.getFrom() + 1, e.getTo() + 1);
            }
        }
        out.println();
    }
}
