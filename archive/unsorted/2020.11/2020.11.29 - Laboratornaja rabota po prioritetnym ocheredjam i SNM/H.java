package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Random;
import java.util.stream.IntStream;

public class H {
    int[] p;
    int[] sz;
    Random rnd;

    int getRoot(int a) {
        while (p[a] != a) {
            a = p[a];
        }
        return a;
    }

    int getDistToRoot(int a) {
        int c = 0;
        while (p[a] != a) {
            c++;
            a = p[a];
        }
        return c;
    }

    boolean getColor(int i) {
        return getDistToRoot(i) % 2 == 0;
    }

    void merge(int i, int j) {
        i = getRoot(i);
        j = getRoot(j);
        if (i == j) {
            return;
        }
        if (sz[j] > sz[i]) {
            int temp = j;
            j = i;
            i = temp;
        }
        p[i] = p[j];
        sz[i] += sz[j];
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        rnd = new Random(n);
        p = IntStream.range(0, n * 2).toArray();
        sz = new int[n * 2];
        int nextFict = n;
        int shift = 0;
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int op = in.nextInt();
            int a = (in.nextInt() - 1 + shift) % n;
            int b = (in.nextInt() - 1 + shift) % n;
            if (op == 1) {
                if (getColor(a) == getColor(b)) {
                    out.println("YES");
                    shift++;
                } else {
                    out.println("NO");
                }
            } else {
                if (getColor(a) != getColor(b)) {
                    merge(a, nextFict);
                    merge(b, nextFict);
                    nextFict++;
                } else {
                    merge(a, b);
                }
            }
        }
    }
}
