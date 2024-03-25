package main;

import main.graphs.*;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1C {
    PrintWriter out;

    static int MAX_N = 27 * 27;
    AdjacencyListGraph<Edge> graph;

    boolean[] used = new boolean[MAX_N];
    int[] p = new int[MAX_N];


    boolean dfs(int v) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        for (Edge e : graph.getChildren(v)) {
            int u = e.getTo();
            if (p[u] == -1 || dfs(p[u])) {
                p[u] = v;
                return true;
            }
        }
        return false;
    }

    int getCode(String s, int i) {
        return (Character.toLowerCase(s.charAt(i)) - 'a') * 27 + (Character.toLowerCase(s.charAt(i + 1)) - 'a');
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String s = in.nextWord();
        char ch = 'z' + 1;
        s = ch + s + ch;
        graph = new AdjacencyListGraph<Edge>(MAX_N);
        for (int i = 1; i < s.length() - 1; i += 2) {
            int code_prev = getCode(s, i - 1);
            int code = getCode(s, i);
            graph.addEdge(new Edge(code, code_prev));
            if (i < s.length() - 2) {
                int code_next = getCode(s, i + 1);
                graph.addEdge(new Edge(code, code_next));
            }
        }
        Arrays.fill(p, -1);
        for (int i = 0; i < MAX_N; ++i) {
            Arrays.fill(used, false);
            dfs(i);
        }
        int ans = Arrays.stream(p).map((i) -> i != -1 ? 1 : 0).sum();
        out.println(ans);
    }
}
