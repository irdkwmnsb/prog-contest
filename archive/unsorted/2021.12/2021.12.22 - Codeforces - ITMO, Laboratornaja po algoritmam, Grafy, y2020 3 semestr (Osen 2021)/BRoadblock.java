package main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Stream;

public class BRoadblock {
    static int INF = (int) 1e9+7;
    PrintWriter out;
    int[][] g;
    int n;
    boolean[] used;
    long[] dist;
    int[] prev;

    void dijkstra() {
        Arrays.fill(dist, INF);
        Arrays.fill(used, false);
        Arrays.fill(prev, -1);
        dist[0] = 0;
        while (true) {
            int min_ind = -1;
            for (int i = 0; i < n; i++) {
                if (!used[i] && (min_ind == -1 || dist[i] < dist[min_ind])) {
                    min_ind = i;
                }
            }
            if (min_ind == -1) {
                break;
            }
            used[min_ind] = true;
            for (int i = 0; i < n; i++) {
                long nw = dist[min_ind] + g[min_ind][i];
                if (nw < dist[i]) {
                    dist[i] = nw;
                    prev[i] = min_ind;
                }
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt();
        int m = in.nextInt();
        g = Stream.generate(() -> {int[] a = new int[n]; Arrays.fill(a, INF); return a;}).limit(n).toArray(int[][]::new);
        dist = new long[n];
        used = new boolean[n];
        prev = new int[n];
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int w = in.nextInt();
            g[from][to] = w;
            g[to][from] = w;
        }
        dijkstra();
        long original = dist[n - 1];
        IntList path = new IntList();
        for (int i = n - 1; i != -1; i = prev[i]) {
            path.push(i);
        }
        long ans = 0;
        for (int i = 0; i + 1 < path.length(); i++) {
            int a = path.get(i), b = path.get(i + 1);
            g[a][b] *= 2;
            g[b][a] *= 2;
            dijkstra();
            ans = Math.max(ans, dist[n - 1] - original);
            g[a][b] /= 2;
            g[b][a] /= 2;
        }
        out.println(ans);
    }
}
