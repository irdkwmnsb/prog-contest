package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T extends Comparable<T>> {
    private final List<T> a;

    public Heap() {
        this.a = new ArrayList<>();
    }

    public T getBest() {
        return a.get(0);
    }

    public int getSize() {
        return a.size();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void push(T val) {
        a.add(val);
        siftUp(a.size() - 1);
    }

    public void pop() {
        if (a.size() != 1) {
            a.set(0, a.remove(a.size() - 1));
            siftDown(0);
        } else {
            a.remove(0);
        }
    }

    public T extractBest() {
        T res = getBest();
        pop();
        return res;
    }

    private void siftDown(int i) {
        while (2 * i + 1 < getSize()) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < getSize() && a.get(right).compareTo(a.get(left)) > 0)
                j = right;
            if (a.get(i).compareTo(a.get(j)) >= 0)
                break;
            swap(i, j, a);
            i = j;
        }
    }

    private static void swap(int i, int j, List a) {
        Object b = a.get(i);
        a.set(i, a.get(j));
        a.set(j, b);
    }

    private void siftUp(int i) {
        while (a.get(i).compareTo(a.get((i - 1) / 2)) > 0) {     // i  0 — мы в корне
            swap(i, (i - 1) / 2, a);
            i = (i - 1) / 2;
        }
    }
}
