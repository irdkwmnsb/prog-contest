package main;

import java.io.PrintWriter;

public class G {
    private static final long MOD = 1000000000;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        IntTreap treap = new IntTreap();
        int n = in.nextInt();
        long lastRes = 0;
        for (int i = 0; i < n; i++) {
            String op = in.nextWord();
            if (op.charAt(0) == '+') {
                int arg = in.nextInt();
                treap.insert(Math.toIntExact((arg + lastRes) % MOD));
                lastRes = 0;
            } else {
                int arga = in.nextInt();
                int argb = in.nextInt();
                lastRes = treap.sum(arga, argb);
                out.println(lastRes);
            }
        }
    }
}
