package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class APinkFloyd {
    PrintWriter out;

    static int INF = (int) (1e9 + 7);

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] g = Utils.makeIntMatrix(n, n);
        int[][] p = Utils.makeIntMatrix(n, n);
        int[] edges = new int[m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], -INF);
            g[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int b = in.nextInt() - 1, e = in.nextInt() - 1, w = in.nextInt();
            edges[i] = e;
            if (g[b][e] < w) {
                g[b][e] = w;
                p[b][e] = i;
            }
        }
        for (int kk = 0; kk < n; kk++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (g[i][kk] != -INF && g[kk][j] != -INF) {
                        if (g[i][kk] + g[kk][j] > g[i][j]) {
                            g[i][j] = g[i][kk] + g[kk][j];
                            if (i == kk) {
                                p[i][j] = p[kk][j];
                            } else {
                                p[i][j] = p[i][kk];
                            }
                        }
                    }
                }
            }
        }
        int[] route = new int[k];
        for (int i = 0; i < k; i++) {
            route[i] = in.nextInt() - 1;
        }
        IntList path = new IntList();
        for (int i = 1; i < k; i++) {
            int b = route[i - 1];
            int e = route[i];
            for (int j = 0; j < n; j++) {
                if (g[b][j] != -INF && g[j][e] != -INF && g[j][j] > 0) {
                    out.println("infinitely kind");
                    return;
                }
            }
            while (b != e) {
                path.push(p[b][e] + 1);
                b = edges[p[b][e]];
            }
        }
        out.println(path.length());
        out.println(Arrays.stream(path.asArray()).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }
}
