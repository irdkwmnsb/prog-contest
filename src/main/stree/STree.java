package main.stree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class STree<T> {

    class Node {
        public Node(T value) {
            this.value = value;
        }
        T value;
        T push_value;
        int size = 0;
    }

    final BinaryOperator<T> f;
    final BiFunction<T, Integer, T> fs;
    final T neutral;
    int n;
    ArrayList<Node> tree;

    public STree(int n, BinaryOperator<T> f, BiFunction<T, Integer, T> fs, T neutral) {
        this.f = f;
        this.n = n;
        this.fs = fs;
        this.neutral = neutral;
        tree = new ArrayList<>();
        for (int i = 0; i < 4 * n; i++) {
            tree.add(new Node(neutral));
        }
    }

    public STree(List<T> a, BinaryOperator<T> f, BiFunction<T, Integer, T> fs, T neutral) {
        this(a.size(), f, fs, neutral);
        if (n > 0)
            build(1, 0, n, a);
    }

    void build(int i, int L, int R, List<T> a) {
        if (L == R) {
            if (L < n) {
                tree.get(i).value = a.get(L);
                tree.get(i).size = 1;
            }
        } else {
            int M = (L + R) / 2;
            build(i * 2, L, M, a);
            build(i * 2 + 1, M + 1, R, a);
            tree.get(i).value = f.apply(tree.get(i * 2).value, tree.get(i * 2 + 1).value);
            tree.get(i).size = tree.get(i * 2).size + tree.get(i * 2 + 1).size;
        }
    }

    T get(int l, int r) {
        return get(1, 0, n, l, r);
    }

    T get(int i, int L, int R, int l, int r) {
//        push(i);
        if (l > r) {
            return neutral;
        } else if (l == L && R == r) {
            return tree.get(i).value;
        } else {
            int M = (L + R) / 2;
            return f.apply(get(2 * i, L, M, l, Math.min(r, M)),
                    get(2 * i + 1, M + 1, R, Math.max(l, M + 1), r));
        }
    }

    void set(int i, T val) {
        _set(1, 0, n, i, val);
    }

    void _set(int i, int L, int R, int w, T val) {
//        push(i);
        if (L >= R) {
            tree.get(i).value = val;
            tree.get(i).size = 1;
        } else {
            int M = (L + R) / 2;
            if (w <= M)
                _set(2 * i, L, M, w, val);
            else
                _set(2 * i + 1, M + 1, R, w, val);
            tree.get(i).value = f.apply(tree.get(i * 2).value, tree.get(i * 2 + 1).value);
            tree.get(i).size = tree.get(i * 2).size + tree.get(i * 2 + 1).size;
        }

    }

//    void add(int i, int j, T val) {
//        add(1, 0, n, i, j, val);
//    }
//
//    void add(int i, int L, int R, int l, int r, T val) {
//        push(i);
//        if (R >= l && r >= L) {
//            if (r <= R && L <= l) {
//                tree.get(i).push_value = f.apply(val, tree.get(i).push_value);
//                push(i);
//            } else {
//                int M = (L + R) / 2;
//                add(l(i), L, M, l, r, val);
//                add(2 * i + 1, M + 1, R, l, r, val);
//                tree.get(i).value = f.apply(tree.get(i * 2).value, tree.get(i * 2 + 1).value);
//            }
//        }
//
//    }

//    void push(int i) {
//        Node inode = tree.get(i);
//        if (inode.push_value != null) {
//            inode.value = f.apply(inode.value, fs.apply(inode.push_value, inode.size));
//            if (i * 2 + 1 < tree.size()) {
//                tree.get(i * 2).push_value = f.apply(tree.get(i * 2).push_value, inode.push_value);
//                tree.get(i * 2 + 1).push_value = f.apply(tree.get(i * 2 + 1).push_value, inode.push_value);
//            }
//            inode.push_value = neutral;
//        }
//    }
}
