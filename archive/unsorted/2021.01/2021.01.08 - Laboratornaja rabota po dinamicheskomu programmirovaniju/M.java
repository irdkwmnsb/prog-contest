package main;

import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;
import main.graphs.Graph;
import main.graphs.WeightedLongEdge;

import java.io.PrintWriter;
import java.util.Arrays;

public class M {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        Graph<WeightedLongEdge> g = AdjacencyListGraph.readWeightedLongGraph(in);
        long[][] dp = new long[1 << g.getN()][g.getN()]; // dp[mask][n] - answer for subtree having visited masked vertices and n is the last one.
        for (int i = 0; i < (1 << g.getN()); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < (g.getN()); i++) {
            dp[(1 << i)][i] = 0;
        }
        for (int mask = 1; mask < (1 << g.getN()); mask++) {
            if ((mask & (mask - 1)) != 0) {
                for (int v = 0; v < g.getN(); v++) {
                    if ((mask & (1 << v)) != 0) {
                        for (WeightedLongEdge e : g.getChildren(v)) {
                            if ((mask & (1 << e.getTo())) != 0) {
                                dp[mask][v] = Math.min(dp[mask][v], dp[mask & ~(1 << v)][e.getTo()] + e.getW());
                            }
                        }
                    }
                }
            }
        }
        long ans = Integer.MAX_VALUE;
        for (int i = 0; i < g.getN(); i++) {
            ans = Math.min(ans, dp[(1 << g.getN()) - 1][i]);
        }
        if (ans == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
            out.println(ans);
        }
    }
}
