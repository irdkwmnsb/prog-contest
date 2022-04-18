package main;

import main.Scanner;
import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class E {
    PrintWriter out;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        AdjacencyListGraph<Edge> g = AdjacencyListGraph.readTreeFromEdgeList(in);
        out.println(IntStream.of(g.getPruferCode()).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        this.out = out;
    }
}
