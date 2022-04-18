package main;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Treap<T extends Comparable<T>> {
    private static final Random treapRandom = new Random();
    private int nodeCount = 0;

    private class Node<TN extends Comparable<TN>> {
        private Node<TN> l = null, r = null;
        private final TN v;
        private final int c;
        private final int n = nodeCount++;
        private int size = 1;

        public Node(TN v) {
            this.v = v;
            this.c = treapRandom.nextInt();
        }

        public Node(TN val, int prior) {
            this.v = val;
            this.c = prior;
        }
    }

    private Node<T> root = null;

    class SplitResult {
        final public Node<T> f, s;

        public SplitResult(Node<T> f, Node<T> s) {
            this.f = f;
            this.s = s;
        }
    }

    private int getSize(Node<T> a) {
        if (a == null)
            return 0;
        else
            return a.size;
    }

    private int getSum(Node<T> a) {
        if (a == null)
            return 0;
        else
            return a.size;
    }

    private void update(Node<T> a) {
        a.size = 1;
        if (a.l != null) {
            a.size += a.l.size;
        }
        if (a.r != null) {
            a.size += a.r.size;
        }
    }

    private SplitResult splitLeft(Node<T> t, T k) {
        if (t == null) {
            return new SplitResult(null, null);
        }
        if (k.compareTo(t.v) < 0) {
            SplitResult res = splitLeft(t.r, k);
            t.r = res.f;
            update(t);
            return new SplitResult(t, res.s);
        } else {
            SplitResult res = splitLeft(t.l, k);
            t.l = res.s;
            update(t);
            return new SplitResult(res.f, t);
        }
    }

    private SplitResult splitRight(Node<T> t, T k) {
        if (t == null) {
            return new SplitResult(null, null);
        }
        if (k.compareTo(t.v) > 0) {
            SplitResult res = splitRight(t.l, k);
            t.l = res.s;
            update(t);
            return new SplitResult(res.f, t);
        } else {
            SplitResult res = splitRight(t.r, k);
            t.r = res.f;
            update(t);
            return new SplitResult(t, res.s);
        }
    }

    private Node<T> merge(Node<T> t1, Node<T> t2) {
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

    public void insert(T val) {
        delete(val);
        SplitResult r = splitLeft(root, val);
        root = merge(r.f, merge(new Node<T>(val), r.s));
    }

    public void insertWithPrior(T val, int prior) {
        delete(val);
        SplitResult r = splitLeft(root, val);
        root = merge(r.f, merge(new Node<T>(val, prior), r.s));
    }

    public T minVal() {
        return minVal(root);
    }

    private T minVal(Node<T> searchNode) {
        Node<T> node = searchNode;
        while (node.l != null) node = node.l;
        return node.v;
    }

    public T maxVal() {
        return maxVal(root);
    }

    private T maxVal(Node<T> searchNode) {
        Node<T> node = searchNode;
        while (node.r != null) node = node.r;
        return node.v;
    }

    public void delete(T val) {
        root = delete(root, val);
    }

    private Node<T> delete(Node<T> node, T v) {
        if (node != null) {
            int compare = v.compareTo(node.v);
            if (compare > 0) {
                node.l = delete(node.l, v);
                update(node);
            } else if (compare < 0) {
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

    public boolean contains(T item) {
        Node<T> node = root;
        while (node != null) {
            int compare = item.compareTo(node.v);
            if (compare < 0) {
                node = node.l;
            } else if (compare > 0) {
                node = node.r;
            } else {
                return true;
            }
        }
        return false;
    }

    public T getNext(T v) {
        SplitResult res = splitRight(root, v);
        if (res.s == null)
            return null;
        T ret = minVal(res.s);
        root = merge(res.f, res.s);
        return ret;
    }

    public T getPrev(T v) {
        SplitResult res = splitLeft(root, v);
        if (res.f == null)
            return null;
        T ret = maxVal(res.f);
        root = merge(res.f, res.s);
        return ret;
    }

    protected void dump(PrintWriter out) {
        List<Node<T>> nodes = new ArrayList<>(nodeCount);
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

    private void dumpNode(Node<T> n, int p, List<Node<T>> l, int[] pp) {
        if (n == null) {
            return;
        }
        l.set(n.n, n);
        pp[n.n] = p;
        dumpNode(n.l, n.n, l, pp);
        dumpNode(n.r, n.n, l, pp);
    }

    public T getKth(int k) {
        Node<T> cur = root;
        while (cur != null) {
            int r = getSize(cur.r);
            int cs = getSize(cur);
            if (cs - r > k) {
                cur = cur.l;
            } else if (cs - r < k) {
                cur = cur.r;
                k -= cs - r;
            } else {
                return cur.v;
            }
        }
        return null;
    }

    public List<T> toList() {
        List<T> ans = new ArrayList<>();
        if (root != null)
            iterate(root, ans);
        return ans;
    }

    void iterate(Node<T> n, List<T> l) {
        if (n.l != null)
            iterate(n.l, l);
        l.add(n.v);
        if (n.r != null)
            iterate(n.r, l);
    }
}
