package main;

import main.Scanner;
import main.stree.IntSTree;
import main.stree.LongSTree;

import java.io.PrintWriter;

public class G {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        long[] arr = in.readLongArray(n);
        LongSTree minstree = new LongSTree(arr, Long::min, (long e, long x) -> e, Long.MAX_VALUE);
        while (in.hasNextWord()) {
            String op = in.nextWord();
            switch (op) {
                case "min": {
                    int i = in.nextInt();
                    int j = in.nextInt();
                    out.println(minstree.get(i - 1, j - 1));
                    break;
                }
                case "set": {
                    int i = in.nextInt();
                    int j = in.nextInt();
                    int x = in.nextInt();
                    minstree.set(i - 1, j - 1, x);
                    break;
                }
                case "add": {
                    int i = in.nextInt();
                    int j = in.nextInt();
                    int x = in.nextInt();
                    minstree.add(i - 1, j - 1, x);
                    break;
                }
            }
        }
    }
}
