package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

public class IntTreap {
    private static final Random treapRandom = new Random();
    private int nodeCount = 0;

    private class Node {
        private Node l = null, r = null;
        private final int v;
        private final int c;
        private final int n = nodeCount++;
        private int size = 1;
        private long sum;

        public Node(int v) {
            this.v = v;
            this.sum = v;
            this.c = treapRandom.nextInt();
        }

        public Node(int val, int prior) {
            this.v = val;
            this.sum = v;
            this.c = prior;
        }
    }

    private Node root = null;

    class SplitResult {
        final public Node l, r;

        public SplitResult(Node f, Node s) {
            this.l = f;
            this.r = s;
        }
    }

    private int getSize(Node a) {
        if (a == null)
            return 0;
        else
            return a.size;
    }

    private long getSum(Node a) {
        if (a == null)
            return 0;
        else
            return a.sum;
    }

    private void update(Node a) {
        a.size = 1;
        a.sum = a.v;
        if (a.l != null) {
            a.size += a.l.size;
            a.sum += a.l.sum;
        }
        if (a.r != null) {
            a.size += a.r.size;
            a.sum += a.r.sum;
        }
    }

    private SplitResult split(Node t, long k) {
        if (t == null) {
            return new SplitResult(null, null);
        }
        if (k > t.v) {
            SplitResult res = split(t.r, k);
            t.r = res.l;
            update(t);
            return new SplitResult(t, res.r);
        } else {
            SplitResult res = split(t.l, k);
            t.l = res.r;
            update(t);
            return new SplitResult(res.l, t);
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

    public void insert(int val) {
        delete(val);
        SplitResult r = split(root, val);
        root = merge(r.l, merge(new Node(val), r.r));
    }

    public void insertWithPrior(int val, int prior) {
        delete(val);
        SplitResult r = split(root, val);
        root = merge(r.l, merge(new Node(val, prior), r.r));
    }

    public int minVal() {
        return minVal(root);
    }

    private int minVal(Node searchNode) {
        Node node = searchNode;
        while (node.l != null) node = node.l;
        return node.v;
    }

    public int maxVal() {
        return maxVal(root);
    }

    private int maxVal(Node searchNode) {
        Node node = searchNode;
        while (node.r != null) node = node.r;
        return node.v;
    }

    public void delete(long val) {
        root = delete(root, val);
    }

    private Node delete(Node node, long v) {
        if (node != null) {
            if (v < node.v) {
                node.l = delete(node.l, v);
                update(node);
            } else if (v > node.v) {
                node.r = delete(node.r, v);
                update(node);
            } else {
                if (node.l == null) {
                    return node.r;
                } else if (node.r == null) {
                    return node.l;
                } else {
                    return merge(node.l, node.r);
                }
            }
        }
        return node;
    }

    public boolean contains(int item) {
        Node node = root;
        while (node != null) {
            if (item > node.v) {
                node = node.l;
            } else if (item < node.v) {
                node = node.r;
            } else {
                return true;
            }
        }
        return false;
    }

    protected void dump(PrintWriter out) {
        List<Node> nodes = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(null);
        }
        int[] p = new int[nodeCount];
        dumpNode(root, -1, nodes, p);
        for (int i = 0; i < nodeCount; i++) {
            out.print(p[i] + 1);
            out.print(" ");
            if (nodes.get(i).l == null) {
                out.print(0);
            } else {
                out.print(nodes.get(i).l.n + 1);
            }
            out.print(" ");
            if (nodes.get(i).r == null) {
                out.print(0);
            } else {
                out.print(nodes.get(i).r.n + 1);
            }
            out.println();
        }
    }
    private void dumpNode(Node n, int p, List<Node> l, int[] pp) {
        if (n == null) {
            return;
        }
        l.set(n.n, n);
        pp[n.n] = p;
        dumpNode(n.l, n.n, l, pp);
        dumpNode(n.r, n.n, l, pp);
    }

    public OptionalInt getKth(int k) {
        Node cur = root;
        while (cur != null) {
            int r = getSize(cur.r);
            int cs = getSize(cur);
            if (cs - r > k) {
                cur = cur.l;
            } else if (cs - r < k) {
                cur = cur.r;
                k -= cs - r;
            } else {
                return OptionalInt.of(cur.v);
            }
        }
        return OptionalInt.empty();
    }

    public long sum(int l, int r) {
        SplitResult s1 = split(root, l);
        SplitResult s2 = split(s1.r, r + 1);
        long ans = getSum(s2.l);
        root = merge(s1.l, merge(s2.l, s2.r));
        return ans;
    }
}
