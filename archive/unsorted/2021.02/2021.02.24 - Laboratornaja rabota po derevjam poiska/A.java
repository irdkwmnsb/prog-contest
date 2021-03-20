package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Objects;

public class A {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        Treap<Integer> treap = new Treap<>();
        while (in.hasNextWord()) {
            String action = in.nextWord();
            int val = in.nextInt();
            switch (action.charAt(0)) {
                case 'i':
                    treap.insert(val);
                    break;
                case 'd':
                    treap.delete(val);
                    break;
                case 'e':
                    out.println(treap.contains(val));
                    break;
                case 'n':
                    out.println(Objects.requireNonNullElse(treap.getNext(val), "none"));
                    break;
                case 'p':
                    out.println(Objects.requireNonNullElse(treap.getPrev(val), "none"));
                    break;
            }
        }
    }
}
