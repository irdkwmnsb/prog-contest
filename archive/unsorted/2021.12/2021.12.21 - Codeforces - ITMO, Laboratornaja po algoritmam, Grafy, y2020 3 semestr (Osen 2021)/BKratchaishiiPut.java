package main;

import main.Scanner;
import main.graphs.AdjacencyListGraph;
import main.graphs.GraphUtils;
import main.graphs.WeightedLongEdge;

import java.io.PrintWriter;

public class BKratchaishiiPut {
    PrintWriter out;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt(), s = in.nextInt(), t = in.nextInt();
        AdjacencyListGraph<WeightedLongEdge> g = AdjacencyListGraph.readWeightedLongGraphNM(in, true, n, m);
        long[] r = GraphUtils.distancesFromStartPoint(g, s - 1);
        if(r[t - 1] == Long.MAX_VALUE) {
            out.println("Unreachable");
        } else {
            out.println(r[t-1]);
        }
    }
}
