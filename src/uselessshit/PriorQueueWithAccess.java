package main;

public class PriorQueueWithAccess {
    static class Entry {
        private final int ind, val;

        Entry(int ind, int val) {
            this.ind = ind;
            this.val = val;
        }

        public int getInd() {
            return ind;
        }

        public int getVal() {
            return val;
        }
    }

    private final IntList a;
    private final IntList ind;
    private final IntList entryInds;

    public PriorQueueWithAccess() {
        this.entryInds = new IntList();
        this.a = new IntList();
        this.ind = new IntList();
    }

    public Entry getMin() {
        return new Entry(ind.get(0), a.get(0));
    }

    public int getSize() {
        return a.length();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public Entry get(int i) {
        return new Entry(i, a.get(entryInds.get(i)));
    }

    public void set(int i, int val) {
        int oldVal = a.get(entryInds.get(i));
        a.set(entryInds.get(i), val);
        if (val < oldVal) {
            siftUp(entryInds.get(i));
        } else {
            siftDown(entryInds.get(i));
        }
    }

    public void push(int val) {
        ind.push(a.length());
        entryInds.push(a.length());
        a.push(val);
        siftUp(a.length() - 1);
    }

    private void siftDown(int i) {
        while (2 * i + 1 < getSize()) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < getSize() && a.get(right) < a.get(left))
                j = right;
            if (a.get(i) <= a.get(j))
                break;
            swap(i, j, a);
            swap(i, j, ind);
            entryInds.set(ind.get(i), i);
            entryInds.set(ind.get(j), j);
            i = j;
        }
    }

    private static void swap(int i, int j, IntList a) {
        int b = a.get(i);
        a.set(i, a.get(j));
        a.set(j, b);
    }

    private void siftUp(int i) {
        while (a.get(i) < a.get((i - 1) / 2)) {     // i  0 — мы в корне
            swap(i, (i - 1) / 2, a);
            swap(i, (i - 1) / 2, ind);
            entryInds.set(ind.get(i), i);
            entryInds.set(ind.get((i - 1) / 2), (i - 1) / 2);

            i = (i - 1) / 2;
        }
    }
}
