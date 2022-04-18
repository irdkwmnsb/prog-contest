package main;

import main.Scanner;
import main.graphs.AdjacencyListGraph;
import main.graphs.Edge;
import main.graphs.Graph;
import main.graphs.MatrixGraph;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Stream;

public class B {
    PrintWriter out;

    boolean[][] g;
    int n;

    public IntList findHamiltonianCycle() {
        Random random = new Random(1);
        IntList q = new IntList();
        for (int i = 0; i < n; i++) {
            q.push(i);
        }
        for (int t = 0; t < 15000; t++) {
            int back = 0;
            for (int k = 0; k < n * (n - 1); k++) {
                if (!g[q.get(back)][q.get(1 + back)]) {
                    int i = 2;
                    while (!g[q.get(back)][q.get(i + back)] || !g[q.get(1 + back)][q.get(i + 1 + back)]) {
                        i++;
                    }
                    Utils.reverse(q, 1 + back, i + back);
                }
                q.push(q.get(back));
                back++;
            }
            q.shuffle(random);
            if (check(q)) {
                return q;
            }
        }
        return null;
    }

    private boolean check(IntList q) {
        if (q.length() != n) {
            return false;
        }

        if (!g[q.top()][q.get(0)]) {
            return false;
        }

        for (int i = 1; i < n; ++i) {
            if (!g[q.get(i - 1)][q.get(i)]) {
                return false;
            }
        }
        return true;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt();
        g = new boolean[n][];
        for (int i = 0; i < n; i++) {
            g[i] = new boolean[n];
        }
        for (int i = 1; i < n; i++) {
            String line = in.nextWord();
            for (int j = 0; j < i; j++) {
                if (line.charAt(j) == '1') {
                    g[i][j] = true;
                    g[j][i] = true;
                }
            }
        }
        IntList cycle = findHamiltonianCycle();
        for (int i = 0; i < cycle.length(); i++) {
            out.print(cycle.get(i) + 1);
            out.print(" ");
        }
        out.println();
    }
}
