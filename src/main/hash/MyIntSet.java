package main.hash;

import main.IntList;

import java.util.ArrayList;
import java.util.List;

public class MyIntSet {
    public MyIntSet() {
        for (int i = 0; i < capacity; i++)
            this.table[i] = new FastIntList();
    }


    private final int capacity = 20000;
    private final FastIntList[] table = new FastIntList[capacity];


    private int indexFor(int h) {
        return Math.abs(h % capacity);
    }

    public void insert(int v) {
        table[indexFor(v)].add(v);
    }

    public void remove(int v) {
        table[indexFor(v)].remove(v);
    }

    public boolean contains(int v) {
        int index = indexFor(v);
        return table[index].contains(v);
    }

    static class FastIntList {
        private final IntList a = new IntList();
        private final IntList exists = new IntList();

        boolean contains(int x) {
            var idx = a.indexOf(x);
            return idx != -1 && (exists.get(idx) == 1);
        }

        void add(int x) {
            var idx = a.indexOf(x);
            if (idx != -1) {
                exists.set(idx, 1);
            } else {
                a.push(x);
                exists.push(1);
            }
        }

        void remove(int x) {
            var idx = a.indexOf(x);
            if (idx != -1) {
                exists.set(idx, 0);
            }
        }
    }
}
