package main;


import main.Scanner;
import main.graphs.AdjacencyListGraph;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AProtivopozharnayaBezopasnost {
    PrintWriter out;

    IntList[] g, gr;
    boolean[] used;
    int[] color, repr;
    IntList top;

    void dfs1(int v) {
        used[v] = true;
        for (int i = 0; i < g[v].length(); i++) {
            int to = g[v].get(i);
            if (!used[to]) {
                dfs1(to);
            }
        }
        top.push(v);
    }

    void dfs2(int v, int c) {
        color[v] = c;
        repr[c] = v;
        for (int i = 0; i < gr[v].length(); i++) {
            int to = gr[v].get(i);
            if (color[to] == -1) {
                dfs2(to, c);
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt();
        g = Stream.generate(IntList::new).limit(n).toArray(IntList[]::new);
        gr = Stream.generate(IntList::new).limit(n).toArray(IntList[]::new);;
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            g[from].push(to);
            gr[to].push(from);
        }
        used = new boolean[n];
        top = new IntList();
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs1(i);
            }
        }
        Utils.reverse(top, 0, top.length() - 1);
        color = new int[n];
        Arrays.fill(color, -1);
        repr = new int[n];
        Arrays.fill(repr, -1);
        int c = 0;
        for (int i = 0; i < n; i++) {
            int v = top.get(i);
            if (color[v] == -1) {
                dfs2(v, c);
                c++;
            }
        }
        used = new boolean[c];
        Arrays.fill(used, true);
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length(); j++) {
                int to = g[i].get(j);
                if (color[i] != color[to]) {
                    used[color[i]] = false;
                }
            }
        }
        IntList ans = new IntList();
        for (int i = 0; i < used.length; i++) {
            if(used[i]) {
                ans.push(repr[i] + 1);
            }
        }
        out.println(ans.length());
        out.println(Arrays.stream(ans.asArray()).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }
}
