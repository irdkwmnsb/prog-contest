package main;

import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;
import main.graphs.Graph;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class AEvakuatsiya {
    PrintWriter out;
    static long INF = (long) 1e9+9;
    static int INTINF = (int) 1e9+7;
    private static class DijkstraEntry {
        int v;
        long d;
        static Comparator<DijkstraEntry> comparator = Comparator.comparing((e) -> e.d);

        public DijkstraEntry(int v, long d) {
            this.v = v;
            this.d = d;
        }
    }
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int k = in.nextInt();
        int[] exits = new int[k];
        for(int i = 0; i<k; i++) {
            exits[i] = in.nextInt() - 1;
        }
        int m = in.nextInt();
        Graph<Edge> g = AdjacencyListGraph.readGraphNM(in, false, n, m);
        int[] exit = new int[n];
        Arrays.fill(exit, INTINF);
        long[] d = new long[n];
        Arrays.fill(d, INF);
        PriorityQueue<DijkstraEntry> q = new PriorityQueue<>(DijkstraEntry.comparator);
        for(int i = 0; i<k; i++) {
            int v = exits[i];
            d[v] = 0;
            exit[v] = v;
            q.add(new DijkstraEntry(v, 0));
        }
        while (!q.isEmpty()) {
            DijkstraEntry entry = q.poll();
            if (entry.d > d[entry.v]) {
                continue;
            }

            for(Edge e: g.getChildren(entry.v)) {
                if (d[entry.v] + 1 <= d[e.getTo()]) {
                    d[e.getTo()] = d[entry.v] + 1;
                    exit[e.getTo()] = Math.min(exit[entry.v], exit[e.getTo()]);
                    q.add(new DijkstraEntry(e.getTo(), d[e.getTo()]));
                }
            }
        }
        out.println(Arrays.stream(d).mapToObj(Long::toString).collect(Collectors.joining(" ")));
        out.println(Arrays.stream(exit).map((a) -> a+1).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }
}
