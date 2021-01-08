package main;

import javax.swing.*;
import java.io.PrintWriter;

public class M {
    static class Comparator {
        final int x, y;

        Comparator(int x, int y) {
            this.x = Integer.min(x, y);
            this.y = Integer.max(x, y);
        }

        int compare(int in) {
            if ((in & (1 << x)) == 0 && (in & (1 << y)) != 0) {
                in ^= (1 << x);
                in ^= (1 << y);
            }
            return in;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        Comparator[][] comps = new Comparator[k][];
        for (int i = 0; i < k; i++) {
            m = in.nextInt();
            comps[i] = new Comparator[m];
            for (int j = 0; j < m; j++) {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                comps[i][j] = new Comparator(x, y);
            }
        }
        boolean sorting = true;
        for (int mask = 1; mask < (1 << n); mask++) {
            int maskCopy = mask;
            for (var layer : comps) {
                for (var comp : layer) {
                    maskCopy = comp.compare(maskCopy);
                }
            }
//            out.println(String.format("%s -> %s",
//                    Integer.toBinaryString(mask),
//                    Integer.toBinaryString(maskCopy)));
            if (maskCopy != (1 << Integer.bitCount(mask)) - 1) {
                sorting = false;
            }
        }
        out.println(sorting ? "Yes" : "No");
    }
}
