package main;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1A {
    PrintWriter out;
    int n;
    int m;
    int[] p2;
    int[] p1;
    boolean[][] c;
    boolean[] used1;
    boolean[] used2;

    boolean dfs(int v) {
        used1[v] = true;
        for (int x = 0; x < m; x++) {
            if (!c[v][x] && !used2[x]) {
                used2[x] = true;
                if (p2[x] == -1 || dfs(p2[x])) {
                    p2[x] = v;
                    p1[v] = x;
                    return true;
                }
            }
        }
        return false;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int k = in.nextInt();
        while (k-- != 0) {
            n = in.nextInt();
            m = in.nextInt();
            c = new boolean[n][];
            for (int i = 0; i < n; i++) {
                c[i] = new boolean[m];
                int x;
                while ((x = in.nextInt()) != 0) {
                    c[i][x - 1] = true;
                }
            }
            p1 = IntStream.generate(() -> -1).limit(n).toArray();
            p2 = IntStream.generate(() -> -1).limit(m).toArray();
            boolean found;
            do {
                found = false;
                used1 = new boolean[n];
                used2 = new boolean[m];
                for (int i = 0; i < n; i++) {
                    if (p1[i] == -1 && dfs(i)) {
                        found = true;
                    }
                }
            } while (found);
            int k1 = IntStream.range(0, n).map((i) -> used1[i] ? 1 : 0).sum();
            int k2 = IntStream.range(0, m).map((i) -> used2[i] ? 0 : 1).sum();
            out.println(k1+k2);
            out.println(k1 + " " + k2);
            out.println(IntStream.range(0, n)
                    .filter((i) -> used1[i])
                    .map((i) -> i+1)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "))
            );
            out.println(IntStream.range(0, m)
                    .filter((i) -> !used2[i])
                    .map((i) -> i+1)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "))
            );
        }
    }
}
