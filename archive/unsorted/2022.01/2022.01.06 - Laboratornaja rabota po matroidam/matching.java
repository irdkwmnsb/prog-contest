package main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class matching {
    PrintWriter out;

    private static class Edge {
        int w, ind;

        static public Comparator<Edge> comparator = Comparator.comparing((Edge e) -> e.w);

        public Edge(int w, int ind) {
            this.w = w;
            this.ind = ind;
        }
    }

    int[] color;
    boolean[] used;
    IntList[] match;
    Edge[] edges;

    boolean kuhn(int s) {
        int i, to;
        if (used[s]) return false;
        used[s] = true;
        for (i = 0; i < match[s].length(); i++) {
            to = match[s].get(i);
            if (color[to] == -1 || kuhn(color[to])) {
                color[to] = s;
                return true;
            }
        }
        return false;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        color = new int[n];
        Arrays.fill(color, -1);
        edges = new Edge[n];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            edges[i] = new Edge(a, i);
        }

        Arrays.sort(edges, Edge.comparator);
        Utils.reverse(edges, 0, edges.length - 1);
        match = new IntList[n];
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            match[i] = new IntList();
            for (int j = 0; j < a; j++) {
                match[i].push(in.nextInt() - 1);
            }
        }
        used = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(used, false);
            kuhn(edges[i].ind);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] != -1) {
                ans[color[i]] = i + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(ans[i]);
            out.print(" ");
        }
        out.println();
    }
}
