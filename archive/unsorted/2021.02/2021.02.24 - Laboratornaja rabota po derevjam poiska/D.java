package main;

import main.Scanner;

import java.io.PrintWriter;

public class D {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        Treap<Integer> treap = new Treap<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String op = in.nextWord();
            int arg = in.nextInt();
            if (op.charAt(0) == '+' || op.charAt(0) == '1') {
                treap.insert(arg);
            } else if (op.charAt(0) == '0') {
                out.println(treap.getKth(arg));
            } else if (op.charAt(0) == '-') {
                treap.delete(arg);
            }
        }
    }
}
