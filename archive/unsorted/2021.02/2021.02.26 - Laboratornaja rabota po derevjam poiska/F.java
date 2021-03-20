package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class F {
    PrintWriter out;
    private static final Random treapRandom = new Random(2);
    private static int nodeCount = 0;

    private static class Node {
        private Node l = null, r = null;
        protected final int v;
        private final int c = treapRandom.nextInt();
        private int size = 1;
        private final int nodeNum = ++nodeCount;
        private int min;
        private int max;

        public Node(int v) {
            this.v = v;
            this.min = v;
            this.max = v;
        }
    }

    void update(Node a) {
        a.size = 1;
        a.min = a.v;
        a.max = a.v;
        if (a.l != null) {
            a.size += a.l.size;
            a.min = Math.min(a.min, a.l.min);
            a.max = Math.max(a.max, a.l.max);
        }
        if (a.r != null) {
            a.size += a.r.size;
            a.min = Math.min(a.min, a.r.min);
            a.max = Math.max(a.max, a.r.max);
        }
    }

    int getSize(Node a) {
        if (a == null)
            return 0;
        else
            return a.size;
    }

    Node root = null;

    Node[] split(Node a, int k) {
        if (a == null) {
            return new Node[]{null, null};
        }
        int l = getSize(a.l);
        if (l >= k) {
            Node[] res = split(a.l, k);
            a.l = res[1];
            update(a);
            return new Node[]{res[0], a};
        } else {
            Node[] res = split(a.r, k - l - 1);
            a.r = res[0];
            update(a);
            return new Node[]{a, res[1]};
        }
    }

    private Node merge(Node t1, Node t2) {
        if (t2 == null) {
            return t1;
        }
        if (t1 == null) {
            return t2;
        }
        if (t1.c < t2.c) {
            t1.r = merge(t1.r, t2);
            update(t1);
            return t1;
        } else {
            t2.l = merge(t1, t2.l);
            update(t2);
            return t2;
        }
    }

    int getMin(Node a) {
        Objects.requireNonNull(a);
        if (a.l != null && a.min == a.l.min) {
            return getMin(a.l);
        }
        if (a.min == a.v) {
            return a.size - getSize(a.r);
        }
        if (a.min == a.r.min) {
            return getMin(a.r) + getSize(a.l) + 1;
        }
        throw new IllegalStateException("WTF");
    }

    int getMax(Node a) {
        Objects.requireNonNull(a);
        if (a.max == a.r.max) {
            return getMin(a.r) + getSize(a.l) + 1;
        }
        if (a.max == a.v) {
            return a.size - getSize(a.r);
        }
        if (a.l != null && a.max == a.l.max) {
            return getMin(a.l);
        }
        throw new IllegalStateException("WTF");
    }

    void print(Node a) {
        if (a == null) {
            return;
        }
        print(a.l);
        out.print(a.v);
        out.print(" ");
        print(a.r);
    }

    void dump(Node a, List<Node> l) {
        if (a == null) {
            return;
        }
        dump(a.l, l);
        l.add(a);
        dump(a.r, l);
    }

//    static int MAXN = 131072 * 2 + 10;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt(), m = in.nextInt();
        Node root = null;
        int MAXN = n + m;
        for (int i = 0; i < MAXN; i++) {
            root = merge(root, new Node(0));
        }
        for (int i = 0; i < n; i++) {
            int op = in.nextInt();

//            out.println("=== " + op + " ===");

            Node[] sr1 = split(root, op - 1);

//            out.println("Leaving:");
//            print(sr1[0]);
//            out.println();

            int min = getMin(sr1[1]);

//            out.println("Min index: " + min);

            Node[] sr2 = split(sr1[1], min - 1);

//            out.println("Left to min:");
//            print(sr2[0]);
//            out.println("");

            Node[] sr3 = split(sr2[1], 1);

//            out.println("Without 0:");
//            print(sr3[1]);
//            out.println("");

            root = merge(sr1[0], merge(new Node(i + 1), merge(sr2[0], sr3[1])));

//            out.println("Merged:");
//            print(root);
//            out.println();
        }
        List<Node> l = new ArrayList<>();
        dump(root, l);
        int end = MAXN - 1;
        for (int i = MAXN - 1; i >= 0; i--) {
            if (l.get(i).v != 0) {
                end = i;
                break;
            }
        }
        out.println(end + 1);
        for (int i = 0; i <= end; i++) {
            out.print(l.get(i).v);
            out.print(" ");
        }
        out.println();
    }
}
