package main.hash;

class MyLinkedSet<T> {
    final double loadFactor = 0.75;
    int capacity = 2;
    int threshold = (int) (capacity * loadFactor);
    private int size = 0;
    @SuppressWarnings("unchecked")
    MyLinkedSet.Node<T>[] table = (MyLinkedSet.Node<T>[]) new MyLinkedSet.Node[capacity];

    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    int indexFor(int h) {
        return h & (capacity - 1);
    }

    private static <T> boolean nodeMatches(MyLinkedSet.Node<T> node, T val, int keyHashCode) {
        return node.hash == keyHashCode && (node.val == val || val.equals(node.val));
    }

    private static <T> MyLinkedSet.Node<T> findInBucket(MyLinkedSet.Node<T> start, T key, int keyHashCode) {
        while (start != null) {
            if (nodeMatches(start, key, keyHashCode)) {
                return start;
            }
            start = start.next;
        }
        return null;
    }

    private static <T> MyLinkedSet.Node<T> findBeforeInBucket(MyLinkedSet.Node<T> start, T key, int keyHashCode) {
        if (start == null) {
            return null;
        }
        while (start.next != null) {
            if (nodeMatches(start.next, key, keyHashCode)) {
                return start;
            }
            start = start.next;
        }
        return null;
    }

    public void insert(T v) {
        int hashCode = v == null ? 0 : v.hashCode();
        int index = indexFor(hash(hashCode));
        MyLinkedSet.Node<T> n = findInBucket(table[index], v, hashCode);
        if (n == null) {
            table[index] = new MyLinkedSet.Node<>(hashCode, v, table[index]);
            size++;
            if (size > threshold)
                resize();
        }
    }

    private void resize() {
        capacity *= 2;
        threshold = (int) (capacity * loadFactor);
        //noinspection unchecked
        MyLinkedSet.Node<T>[] newTable = (MyLinkedSet.Node<T>[]) new MyLinkedSet.Node[capacity];
        for (MyLinkedSet.Node<T> node : table) {
            while (node != null) {
                int newIndex = indexFor(hash(node.hash));
                MyLinkedSet.Node<T> oldFirst = newTable[newIndex];
                MyLinkedSet.Node<T> nextNode = node.next;
                newTable[newIndex] = node;
                node.next = oldFirst;
                node = nextNode;
            }
        }
        table = newTable;
    }

    public void remove(T key) {
        int objHashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(objHashCode));
        if (table[index] != null && nodeMatches(table[index], key, objHashCode)) {
            table[index] = table[index].next;
        } else {
            MyLinkedSet.Node<T> beforeN = findBeforeInBucket(table[index], key, objHashCode);
            if (beforeN == null) {
                return;
            }
            beforeN.next = beforeN.next.next; // remove from bucket
        }
    }

    public boolean contains(T v) {
        int objHashCode = v.hashCode();
        int index = indexFor(hash(objHashCode));
        return findInBucket(table[index], v, objHashCode) != null;
    }

    static class Node<T> {
        final int hash;
        final T val;
        MyLinkedSet.Node<T> next;

        public Node(int hash, T val, MyLinkedSet.Node<T> next) {
            this.hash = hash;
            this.val = val;
            this.next = next;
        }

    }

}