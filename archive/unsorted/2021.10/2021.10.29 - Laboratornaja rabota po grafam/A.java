package main;

import main.Scanner;
import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;

import java.io.PrintWriter;

public class A {
    PrintWriter out;

    boolean[] used;
    AdjacencyListGraph<Edge> g;

    private boolean dfs(int v, IntList cycle) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        cycle.push(v);
        if (cycle.length() == g.getN()) { // full cycle
            if (g.getEdge(v, cycle.get(0)) != null) {
                return true;
            }
        } else {
            for(Edge e: g.getChildren(v)) {
                if(dfs(e.getTo(), cycle)) {
                    return true;
                }
            }
        }
        cycle.pop();
        used[v] = false;
        return false;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        g = AdjacencyListGraph.readSymmetricGraph(in);
        g.sortAdjacencyList();
        int start = 0;
        int start_size = g.getChildren(0).size();
        for (int i = 1; i < g.getN(); i++) {
            int new_size = g.getChildren(i).size();
            if (new_size > start_size) {
                start = i;
                start_size = new_size;
            }
        }
        used = new boolean[g.getN()];
        IntList cycle = new IntList();
        dfs(start, cycle );
        for (int i = 0; i < cycle.length(); i++) { // someday i will implement iterator on IntList...
            out.print(cycle.get(i) + 1);
            out.print(" ");
        }
        out.println();
    }
}
