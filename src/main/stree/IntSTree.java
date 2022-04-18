package main.stree;

import java.util.function.IntBinaryOperator;

public class IntSTree {
    static class Node {
        public Node(int value) {
            this.value = value;
            this.push_value = value;
        }

        int value;
        int push_value;
        int size = 0;
    }

    final IntBinaryOperator f;
    final IntBinaryOperator fs;
    final int neutral;
    int n;
    Node[] tree;

    public IntSTree(int n, IntBinaryOperator f, IntBinaryOperator fs, int neutral) {
        this.f = f;
        this.n = n;
        this.fs = fs;
        this.neutral = neutral;
        tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node(neutral);
        }
    }

    public IntSTree(int[] a, IntBinaryOperator f, IntBinaryOperator fs, int neutral) {
        this(a.length, f, fs, neutral);
        if (n > 0)
            build(1, 0, n, a);
    }

    void build(int i, int L, int R, int[] a) {
        if (L == R) {
            if (L < n) {
                tree[i].value = a[L];
                tree[i].size = 1;
            }
        } else {
            int M = (L + R) / 2;
            build(i * 2, L, M, a);
            build(i * 2 + 1, M + 1, R, a);
            tree[i].value = f.applyAsInt(tree[i * 2].value, tree[i * 2 + 1].value);
            tree[i].size = tree[i * 2].size + tree[i * 2 + 1].size;
        }
    }

    public int get(int l, int r) {
        return get(1, 0, n, l, r);
    }

    int get(int i, int L, int R, int l, int r) {
        push(i);
        if (l > r) {
            return neutral;
        } else if (l == L && R == r) {
            return tree[i].value;
        } else {
            int M = (L + R) / 2;
            return f.applyAsInt(get(2 * i, L, M, l, Math.min(r, M)),
                    get(2 * i + 1, M + 1, R, Math.max(l, M + 1), r));
        }
    }

    public void set(int i, int val) {
        _set(1, 0, n, i, val);
    }

    void _set(int i, int L, int R, int w, int val) {
        push(i);
        if (L >= R) {
            tree[i].value = val;
            tree[i].size = 1;
        } else {
            int M = (L + R) / 2;
            if (w <= M)
                _set(2 * i, L, M, w, val);
            else
                _set(2 * i + 1, M + 1, R, w, val);
            tree[i].value = f.applyAsInt(tree[i * 2].value, tree[i * 2 + 1].value);
            tree[i].size = tree[i * 2].size + tree[i * 2 + 1].size;
        }

    }

    void add(int i, int j, int val) {
        add(1, 0, n, i, j, val);
    }

    void add(int i, int L, int R, int l, int r, int val) {
        push(i);
        if (R >= l && r >= L) {
            if (r <= R && L <= l) {
                tree[i].push_value = f.applyAsInt(val, tree[i].push_value);
                push(i);
            } else {
                int M = (L + R) / 2;
                add(2 * i, L, M, l, r, val);
                add(2 * i + 1, M + 1, R, l, r, val);
                tree[i].value = f.applyAsInt(tree[i * 2].value, tree[i * 2 + 1].value);
            }
        }

    }

    void push(int i) {
        Node inode = tree[i];
        if(inode.push_value != neutral) {
            inode.value = f.applyAsInt(inode.value, fs.applyAsInt(inode.push_value, inode.size));
            if (i * 2 + 1 < tree.length) {
                tree[i * 2].push_value = f.applyAsInt(tree[i * 2].push_value, inode.push_value);
                tree[i * 2 + 1].push_value = f.applyAsInt(tree[i * 2 + 1].push_value, inode.push_value);
            }
            inode.push_value = neutral;
        }
    }
}
