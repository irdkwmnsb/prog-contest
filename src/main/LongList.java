package main;

import java.util.Arrays;
import java.util.Random;

public class LongList {
    protected long[] container = new long[1];
    protected int i = 0;

    public LongList() {

    }

    public LongList(LongList a) {
        container = Arrays.copyOf(a.container, a.container.length);
        i = a.i;
    }

    public LongList(long[] a) {
        this();
        for (long i : a) {
            push(i);
        }
    }

    public void push(long a) {
        container[i] = a;
        i++;
        if (i == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    public int length() {
        return i;
    }

    public long top() {
        if (i > 0) {
            return container[i - 1];
        } else {
            throw new IndexOutOfBoundsException("Top on empty IntList");
        }
    }

    public long get(int i) {
        if (0 <= i && i < this.i) {
            return container[i];
        } else {
            throw new IndexOutOfBoundsException("Get out of bounds");
        }
    }

    public void pop() {
        if (i > 0) {
            i--;
        } else {
            throw new IndexOutOfBoundsException("Pop on empty IntList");
        }
    }

    public void set(int i, long a) {
        if (0 <= i && i < this.i) {
            container[i] = a;
        } else {
            throw new IndexOutOfBoundsException("Get out of bounds");
        }
    }

    public boolean remove(long v) {
        return findAndRemove(v, true);
    }

    public long[] asArray() {
        return Arrays.copyOf(container, i);
    }

    public boolean contains(long v) {
        return findAndRemove(v, false);
    }

    private boolean findAndRemove(long v, boolean remove) {
        for (int ind = 0; ind < i; ind++) {
            if (container[ind] == v) {
                if (remove) {
                    i--;
                    System.arraycopy(container, ind + 1, container, ind, (i - ind));
                }
                return true;
            }
        }
        return false;
    }

    public void add(int i, long el) {
        push(0);
        for (int j = this.i - 1; j > i; j--) {
            container[j] = container[j - 1];
        }
        container[i] = el;
    }

    public int indexOf(long x) {
        for (int ind = 0; ind < i; ind++) {
            if (container[ind] == x) {
                return ind;
            }
        }
        return -1;
    }

    public void clear() {
        i = 0;
    }

    public void shuffle(Random rnd) {
        for (int j = 0; j < i; j++) {
            Utils.swap(j, rnd.nextInt(j + 1), container);
        }
    }
}
