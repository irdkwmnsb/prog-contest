package main;

import main.stree.IntSTree;

import java.io.PrintWriter;

public class D {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] arr = in.readIntArray(n);
        IntSTree minstree = new IntSTree(arr, Integer::min, (e, x) -> e, Integer.MAX_VALUE);
        while (in.hasNextWord()) {
            String op = in.nextWord();
            if (op.equals("min")) {
                int i = in.nextInt();
                int j = in.nextInt();
                out.println(minstree.get(i - 1, j - 1));
            } else if (op.equals("set")) {
                int i = in.nextInt();
                int x = in.nextInt();
                minstree.set(i - 1, x);
            }
        }
    }
}
