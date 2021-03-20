package main;

import java.io.PrintWriter;
import java.util.Random;

public class C {
    PrintWriter out;
    private static final Random treapRandom = new Random();

    private static class Node {
        private Node l = null, r = null;
        protected final int v;
        private final int c = treapRandom.nextInt();
        private int size = 1;

        public Node(int v) {
            this.v = v;
        }
    }

    void update(Node a) {
        a.size = 1;
        if (a.l != null)
            a.size += a.l.size;
        if (a.r != null)
            a.size += a.r.size;
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

    void print(Node a) {
        if (a == null) {
            return;
        }
        print(a.l);
        out.print(a.v);
        out.print(" ");
        print(a.r);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int m = in.nextInt();
        Node root = new Node(1);
        for (int i = 1; i < n; i++) {
            root = merge(root, new Node(i + 1));
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            Node[] left = split(root, a - 1);
            Node[] right = split(left[1], (b - a) + 1);
            root = merge(merge(right[0], left[0]), right[1]);
        }
        print(root);
    }
}
