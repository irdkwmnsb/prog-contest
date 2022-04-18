package main.graphs;

import main.IntList;
import main.Utils;

import java.util.stream.LongStream;

public class GraphUtils {
    private static void topSortDfs(Graph<WeightedLongEdge> g, boolean[] visited, IntList ans, int v) {
        visited[v] = true;
        for(Edge e: g.getChildren(v)) {
            if(!visited[e.to]) {
                topSortDfs(g, visited, ans, e.to);
            }
        }
        ans.push(v);
    }

    public static int[] topSort(Graph<WeightedLongEdge> g) {
        boolean[] visited = new boolean[g.getN()];
        IntList ans = new IntList();
        for(int i = 0; i<g.getN(); i++) {
            if(!visited[i]) {
                topSortDfs(g, visited, ans, i);
            }
        }
        Utils.reverse(ans, 0, ans.length()-1);
        return ans.asArray();
    }

    public static long[] distancesFromStartPoint(Graph<WeightedLongEdge> g, int start) {
        long[] d = LongStream.generate(() -> Long.MAX_VALUE).limit(g.getN()).toArray();
        d[start] = 0;
        int[] p = topSort(g);
        for (int i = 0; i < g.getN(); i++) {
            for(WeightedLongEdge e: g.getChildren(p[i])) {
                if(d[p[i]] != Long.MAX_VALUE && d[e.to] > d[p[i]] + e.w) {
                    d[e.to] = d[p[i]] + e.w;
                }
            }
        }
        return d;
    }
}
