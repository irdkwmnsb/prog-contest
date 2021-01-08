package main;

import main.Scanner;

import java.io.PrintWriter;

public class F {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), q = in.nextInt();
        for (int i = 0; i < m; i++) {
            in.nextInt();
            in.nextInt();
        }
        int[][] qs = new int[q][3];
        int asked = 0;
        for (int i = 0; i < q; i++) {
            if (in.nextWord().charAt(0) == 'a') {
                qs[i][0] = 1;
                asked++;
            } else {
                qs[i][0] = 0;
            }
            qs[i][1] = in.nextInt() - 1;
            qs[i][2] = in.nextInt() - 1;
        }
        MinMaxDSU dsu = new MinMaxDSU(n);
        boolean[] answers = new boolean[asked];
        for (int i = q - 1; i >= 0; i--) {
            if (qs[i][0] == 1) { // ask
                answers[--asked] = dsu.get(qs[i][1]).getI() == dsu.get(qs[i][2]).getI();
            } else {
                dsu.merge(qs[i][1], qs[i][2]);
            }
        }
        for(boolean ans: answers) {
            out.println(ans ? "YES" : "NO");
        }
    }
}
