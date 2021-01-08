package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Random;
import java.util.stream.IntStream;

public class D {
    int[] pSet; // Предки по множествам
    int[] pSeg; // Предки по отрезкам
    int[] maxSeg; // Максимальное в отрезках
    Random random;

    int resolve(int i, int[] p) {
        while (p[i] != i) {
            i = p[i];
            p[i] = p[p[i]];
        }
        return i;
    }

    void merge(int i, int j, int[] p) {
        i = resolve(i, p);
        j = resolve(j, p);
        if (random.nextBoolean()) {
            p[j] = i;
        } else {
            p[i] = j;
        }
    }

    void mergeSets(int i, int j) {
        merge(i, j, pSet);
    }

    void mergeSegments(int from, int to) {
        while (maxSeg[resolve(from, pSeg)] + 1 <= to) {
            int fromSeg = resolve(from, pSeg);
            int newFrom = maxSeg[fromSeg] + 1;
            maxSeg[fromSeg] = Integer.max(maxSeg[fromSeg], maxSeg[resolve(newFrom, pSeg)]);
            merge(from, newFrom, pSet);
            merge(from, newFrom, pSeg);
            from = newFrom;
        }
    }

    boolean test(int i, int j) {
        i = resolve(resolve(i, pSeg), pSet);
        j = resolve(resolve(j, pSeg), pSet);
        return i == j;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), q = in.nextInt();
        random = new Random(n);
        pSet = IntStream.range(0, n).toArray();
        pSeg = IntStream.range(0, n).toArray();
        maxSeg = IntStream.range(0, n).toArray();
        for (int i = 0; i < q; i++) {
            switch (in.nextInt()) {
                case 1:
                    mergeSets(in.nextInt() - 1, in.nextInt() - 1);
                    break;
                case 2:
                    mergeSegments(in.nextInt() - 1, in.nextInt() - 1);
                    break;
                default:
                    out.println(test(in.nextInt() - 1, in.nextInt() - 1) ? "YES" : "NO");
                    break;
            }
        }
    }
}
