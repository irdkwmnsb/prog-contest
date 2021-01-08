package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E {
    private IntList[] g;
    private boolean[] isTerm;
    int middle = -1;
    int middleDepth = -1;

    int getDepthAndSaveMiddle(int v, int p, int d) {
        if (isTerm[v]) {
            return 1;
        }
        int maxDepth = 0;
        for (IntList.IntListIterator it = g[v].iterator(); it.hasNext(); ) {
            int u = it.next();
            if (u != p) {
                int childDepth = getDepthAndSaveMiddle(u, v, d + 1);
                if (childDepth + 1 == d && d > middleDepth) {
                    middle = v;
                    middleDepth = d;
                }
                maxDepth = Integer.max(maxDepth, childDepth);
            }
        }
        return maxDepth + 1;
    }

    int[] depths;

    void calcDepths(int v, int p, int d) {
        depths[v] = d;
        for (IntList.IntListIterator it = g[v].iterator(); it.hasNext(); ) {
            int u = it.next();
            if (u != p) {
                calcDepths(u, v, d + 1);
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        g = Stream.generate(IntList::new).limit(n).toArray(IntList[]::new);
        for (int i = 0; i < n - 1; i++) {
            int f = in.nextInt(), t = in.nextInt();
            g[f - 1].push(t - 1);
            g[t - 1].push(f - 1);
        }
        int lastTerm = -1;
        isTerm = new boolean[n];
        for (int i = 0; i < m; i++) {
            lastTerm = in.nextInt();
            lastTerm--;
            isTerm[lastTerm] = true;
        }
        if (m == 1) {
            out.println("YES");
            out.println(lastTerm + 1);
        } else {
            isTerm[lastTerm] = false;
            getDepthAndSaveMiddle(lastTerm, -1, 1);
            isTerm[lastTerm] = true;
            if (middle != -1) {
                depths = new int[n];
                calcDepths(middle, -1, 0);
                boolean middleValid = true;
                for (int i = 0; i < n; i++) {
                    if (isTerm[i] && depths[i] != depths[lastTerm]) {
                        middleValid = false;
                        break;
                    }
                }
                if (middleValid) {
                    out.println("YES");
                    out.println(middle + 1);
                } else {
                    out.println("NO");
                }
            } else {
                out.println("NO");
            }
        }
        out.close();
    }
}
