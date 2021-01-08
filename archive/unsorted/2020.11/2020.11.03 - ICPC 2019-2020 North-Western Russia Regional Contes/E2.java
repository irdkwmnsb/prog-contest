package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class E2 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        IntList[] g = Stream.generate(IntList::new).limit(n).toArray(IntList[]::new);
        for (int i = 0; i < n - 1; i++) {
            int f = in.nextInt(), t = in.nextInt();
            g[f - 1].push(t - 1);
            g[t - 1].push(f - 1);
        }
        int lastTerm = -1;
        boolean[] isTerm = new boolean[n];
        for (int i = 0; i < m; i++) {
            lastTerm = in.nextInt();
            lastTerm--;
            isTerm[lastTerm] = true;
        }
        if (m == 1) {
            out.println("YES");
            out.println(lastTerm + 1);
        } else {
            Queue<Integer> q = new ArrayDeque<>();
            q.add(lastTerm);
            int[] p = new int[n];
            Arrays.fill(p, -2);
            int[] length = new int[n];
            length[lastTerm] = 1;
            p[lastTerm] = -1;
            while (!q.isEmpty()) {
                int v = q.remove();
                for (IntList.IntListIterator it = g[v].iterator(); it.hasNext(); ) {
                    int u = it.next();
                    if (p[u] == -2) {
                        length[u] = length[v] + 1;
                        p[u] = v;
                        q.add(u);
                    }
                }
            }
            int max_i = IntStream.range(0, n).filter((a) -> isTerm[a]).reduce((a, b) -> length[a] < length[b] ? b : a).orElseThrow();
            if (length[max_i] % 2 == 0) {
                out.println("NO");
            } else {
                int mid = p[max_i];
                for (int i = 1; i < length[max_i] / 2; i++) {
                    mid = p[mid];
                }
                q.add(mid);
                Arrays.fill(p, 0);
                length[mid] = 1;
                while (!q.isEmpty()) {
                    int v = q.remove();
                    for (IntList.IntListIterator it = g[v].iterator(); it.hasNext(); ) {
                        int u = it.next();
                        if (p[u] == 0) {
                            length[u] = length[v] + 1;
                            p[u] = 1;
                            q.add(u);
                        }
                    }
                }
                int finalLastTerm = lastTerm;
                boolean midValid = IntStream.range(0, n).filter((a) -> isTerm[a]).allMatch((i) -> length[finalLastTerm] == length[i]);
                if (midValid) {
                    out.println("YES");
                    out.println(mid + 1);
                } else {
                    out.println("NO");
                }
            }
        }
        out.close();
    }
}
