package main;

import main.stree.LongSTree;

import java.io.PrintWriter;

public class E {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        long[] arr = in.readLongArray(n);
        LongSTree minstree = new LongSTree(arr, Long::sum, (long e, long x) -> e * x, 0L);
        while (in.hasNextWord()) {
            String op = in.nextWord();
            if (op.equals("sum")) {
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
