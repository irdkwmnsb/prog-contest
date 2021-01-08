package main;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.PrimitiveIterator;

public class IntList {
    private int[] container = new int[1];
    private int i = 0;
    private boolean iteratorSafe = true;

    public IntList() {

    }

    public IntList(IntList a) {
        container = Arrays.copyOf(a.container, a.container.length);
        i = a.i;
        iteratorSafe = a.iteratorSafe;
    }

    public IntList(int[] a) {
        this();
        for (int i : a) {
            push(i);
        }
    }

    public void push(int a) {
        container[i] = a;
        i++;
        iteratorSafe = false;
        if (i == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    public int length() {
        return i;
    }

    public int top() {
        if (i > 0) {
            return container[i - 1];
        } else {
            throw new IndexOutOfBoundsException("Top on empty IntList");
        }
    }

    public int get(int i) {
        if (0 <= i && i < this.i) {
            return container[i];
        } else {
            throw new IndexOutOfBoundsException("Get out of bounds");
        }
    }

    public void pop() {
        if (i > 0) {
            i--;
            iteratorSafe = false;
        } else {
            throw new IndexOutOfBoundsException("Pop on empty IntList");
        }
    }

    public void set(int i, int a) {
        if (0 <= i && i < this.i) {
            container[i] = a;
        } else {
            throw new IndexOutOfBoundsException("Get out of bounds");
        }
    }

    public class IntListIterator implements PrimitiveIterator.OfInt {
        int i = 0;

        public IntListIterator() {
        }

        @Override
        public int nextInt() {
            if (!iteratorSafe)
                throw new RuntimeException("IntList changed during iteration");
            return container[i++];
        }

        @Override
        public boolean hasNext() {
            return i < IntList.this.i;
        }
    }

    public IntListIterator iterator() {
        this.iteratorSafe = true;
        return new IntListIterator();
    }

    public int[] asArray() {
        return Arrays.copyOf(container, i);
    }
}
