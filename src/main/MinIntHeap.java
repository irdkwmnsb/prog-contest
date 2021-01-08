package main;

class MinIntHeap {
    private final IntList a;

    public MinIntHeap() {
        this.a = new IntList();
    }

    public int getMin() {
        return a.get(0);
    }

    public int getSize() {
        return a.length();
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void push(int val) {
        a.push(val);
        siftUp(a.length() - 1);
    }

    public void pop() {
        a.set(0, a.get(a.length() - 1));
        a.pop();
        siftDown(0);
    }

    public int extractMin() {
        int res = getMin();
        pop();
        return res;
    }

    private void siftDown(int i) {
        while (2 * i + 1 < getSize()) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < getSize() && a.get(right) > a.get(left))
                j = right;
            if (a.get(i) <= a.get(j))
                break;
            swap(i, j, a);
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
            i = (i - 1) / 2;
        }
    }
}

