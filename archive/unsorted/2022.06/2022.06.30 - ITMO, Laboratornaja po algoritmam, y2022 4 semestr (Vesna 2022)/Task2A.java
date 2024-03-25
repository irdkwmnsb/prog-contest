package main;

import main.Scanner;
import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;
import main.graphs.FlowEdge;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Task2A {
    PrintWriter out;
    int S;
    int T;
    int df;
    AdjacencyListGraph<FlowEdge> g;
    int[] u;
    int cc = 1;
    boolean expand(int v, int curFlow) {
        if(v == T) {
            df = curFlow;
            return df != 0;
        }
        u[v] = cc;
        for(FlowEdge e: g.getChildren(v)) {
            if(e.getC() - e.f > 0 && u[e.getTo()] != cc && expand(e.getTo(), Math.min(curFlow, e.getC() - e.f))) {
                e.f += df;
                e.getRev().f -= df;
                return true;
            }
        }
        return false;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        S = 0;
        T = n - 1;
        u = new int[n];
        int m = in.nextInt();
        g = new AdjacencyListGraph<>(n);
        FlowEdge[] edges = new FlowEdge[m];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            FlowEdge e = new FlowEdge(a, b, c, i);
            edges[i] = e;
            g.addEdge(e);
            g.addEdge(e.makeReverse());
        }
        int f = 0;
        while (expand(S, Integer.MAX_VALUE)) {
            cc++;
            f += df;
        }
        out.println(f);
        for(FlowEdge e: edges) {
            out.println(e.f);
        }
    }
}
