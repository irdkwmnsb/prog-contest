package main;



import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class O {
    static class Comparator {
        public Comparator(int x, int y) {
            this.x = x;
            this.y = y;
        }

        final int x, y;
    }

    public static Comparator[] makeNet(int[] inds) {
        int n = inds.length;
        Comparator[] comparators = new Comparator[n * (n - 1) / 2];
        int c = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                comparators[c++] = new Comparator(inds[i], inds[j]);
            }
        }
        return comparators;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        for (int n = in.nextInt(); n != 0; n = in.nextInt()) {
            int[] a = in.readIntArray(n);
            int[] ones = IntStream.range(0, n).filter((i) -> a[i] == 1).toArray();
            int[] zeros = IntStream.range(0, n).filter((i) -> a[i] == 0).toArray();
            boolean isSorted = true;
            for(int i = 0; i<n-1; i++) {
                if (a[i] > a[i + 1]) {
                    isSorted = false;
                    break;
                }
            }
            if (ones.length == 0 || zeros.length == 0 || isSorted) {
                out.println(-1);
                continue;
            }
            List<Comparator> ans = new ArrayList<>();
            Collections.addAll(ans, makeNet(ones));
            Collections.addAll(ans, makeNet(zeros));
            Collections.addAll(ans, makeNet(IntStream.range(0, n).filter((i) -> i != zeros[zeros.length - 1]).toArray()));
            Collections.addAll(ans, makeNet(IntStream.range(zeros.length, n).toArray()));
            out.println(ans.size());
            for (Comparator comp : ans) {
                out.printf("%d %d\n", comp.x + 1, comp.y + 1);
            }
        }
    }
}
