package main.stree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;


// with multi-set
public class STreeHalfIntervals<T> {

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

    public STreeHalfIntervals(int n, BinaryOperator<T> f, BiFunction<T, Integer, T> fs, T neutral) {
        this.f = f;
        this.n = n;
        this.fs = fs;
        this.neutral = neutral;
        tree = new ArrayList<>();
        for (int i = 0; i < 4 * n; i++) {
            tree.add(new Node(neutral));
        }
    }

    int l(int v) {
        return v * 2 + 1;
    }

    int r(int v) {
        return v * 2 + 2;
    }

    void updateSize(int i) {
        tree.get(i).size = tree.get(l(i)).size + tree.get(r(i)).size;
    }

    T getValue(int i) {
        push(i);
        return tree.get(i).value;
    }

    void updateValue(int i) {
        tree.get(i).value = f.apply(getValue(l(i)), getValue(r(i)));
        push(i);
    }

    public STreeHalfIntervals(List<T> a, BinaryOperator<T> f, BiFunction<T, Integer, T> fs, T neutral) {
        this(a.size(), f, fs, neutral);
        if (n > 0)
            build(0, 0, n, a);
    }

    void build(int i, int rl, int rr, List<T> a) {
        if (rl + 1 == rr) {
            if (rl < n) {
                tree.get(i).value = a.get(rl);
                tree.get(i).size = 1;
            }
        } else {
            int M = (rl + rr) / 2;
            build(l(i), rl, M, a);
            build(r(i), M, rr, a);
            updateValue(i);
            updateSize(i);
        }
    }

    T get(int l, int r) {
        return get(0, 0, n, l, r);
    }

    T get(int i, int rl, int rr, int ql, int qr) {
        if (ql <= rl && rr <= qr) {
            return getValue(i);
        } else if (qr <= rl || rr <= ql) {
            return neutral;
        } else {
            int M = (rl + rr) / 2;
            return f.apply(get(l(i), rl, M, ql, qr), get(r(i), M, rr, ql, qr));
        }
    }

    void set(int i, T val) {
        _set(0, 0, n, i, val);
    }

    void _set(int i, int rl, int rr, int w, T val) {
        push(i);
        if (rl + 1 == rr) {
            tree.get(i).value = val;
            tree.get(i).size = 1;
        } else {
            int M = (rl + rr) / 2;
            if (w < M)
                _set(l(i), rl, M, w, val);
            else
                _set(r(i), M, rr, w, val);
            updateValue(i);
            updateSize(i);
        }
    }
    void push(int i) {
        Node inode = tree.get(i);
        if (inode.push_value != null) {
            inode.value = f.apply(inode.value, fs.apply(inode.push_value, inode.size));
            if (r(i) < tree.size()) {
                tree.get(l(i)).push_value = f.apply(tree.get(l(i)).push_value, inode.push_value);
                tree.get(r(i)).push_value = f.apply(tree.get(r(i)).push_value, inode.push_value);
            }
            inode.push_value = neutral;
        }
    }
}
