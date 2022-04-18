package main.hash;

import java.util.Map;
import java.util.Objects;

public class MyLinkedHashMap<K, V> {
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;
        Node<K, V> after;
        Node<K, V> before;

        Node(int hash, K key, V value, Node<K, V> next, Node<K, V> after, Node<K, V> before) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
            this.after = after;
            this.before = before;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                return Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    final double loadFactor = 0.75;
    int capacity = 2;
    int threshold = (int) (capacity * loadFactor);

    Node<K, V> after = null;
    Node<K, V> before = null;

    public int getSize() {
        return size;
    }

    int size = 0;

    @SuppressWarnings({"unchecked"})
    Node<K, V>[] table = (Node<K, V>[]) new Node[capacity];

    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    int indexFor(int h) {
        return h & (capacity - 1);
    }

    private static <K, V> boolean nodeMatches(Node<K, V> node, K key, int keyHashCode) {
        return node.hash == keyHashCode && (node.key == key || key.equals(node.key));
    }

    private Node<K, V> findInBucket(Node<K, V> start, K key, int keyHashCode) {
        while (start != null) {
            if (nodeMatches(start, key, keyHashCode)) {
                return start;
            }
            start = start.next;
        }
        return null;
    }

    private Node<K, V> findBeforeInBucket(Node<K, V> start, K key, int keyHashCode) {
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

    public V put(K key, V val) {
        assert val != null;
        int keyHashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(keyHashCode));
        Node<K, V> n = findInBucket(table[index], key, keyHashCode);
        if (n != null) {
            V oldValue = n.value;
            n.value = val;
            return oldValue;
        } else {
            table[index] = new Node<K, V>(keyHashCode, key, val, table[index], null, before);
            if (after == null) {
                after = table[index];
                before = table[index];
            } else {
                before.after = table[index];
                before = before.after;
            }
            size++;
            if (size > threshold)
                resize();
            return val;
        }
    }

    public void delete(K key) {
        int objHashCode = key == null ? 0 : key.hashCode();
        int index = indexFor(hash(objHashCode));
        Node<K, V> n;
        if (table[index] != null && nodeMatches(table[index], key, objHashCode)) {
            n = table[index];
            table[index] = table[index].next;
        } else {
            Node<K, V> beforeN = findBeforeInBucket(table[index], key, objHashCode);
            if (beforeN == null) {
                return;
            }

            n = beforeN.next;
            if (n == null) {
                throw new AssertionError("Found previous in hashmap but did not find \"it\"");
            }
            beforeN.next = beforeN.next.next; // remove from bucket
        }

        if (after == n) {
            after = n.after;
        } else {
            n.before.after = n.after;
        }
        if (before == n) {
            before = n.before;
        } else {
            n.after.before = n.before;
        }
    }

    private void resize() {
        capacity *= 2;
        threshold = (int) (capacity * loadFactor);
        //noinspection unchecked
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[capacity];
        for (Node<K, V> node : table) {
            while (node != null) {
                int objHashCode = node.key.hashCode();
                int newIndex = indexFor(hash(objHashCode));
                Node<K, V> oldFirst = newTable[newIndex];
                Node<K, V> nextNode = node.next;
                newTable[newIndex] = node;
                node.next = oldFirst;
                node = nextNode;
            }
        }
        table = newTable;
    }

    public V get(K key) {
        Node<K, V> n = lookup(key);
        if (n == null)
            return null;
        return n.value;
    }

    private Node<K, V> lookup(K key) {
        int objHashCode = key.hashCode();
        int index = indexFor(hash(objHashCode));
        return findInBucket(table[index], key, objHashCode);
    }

    public V prev(K key) {
        Node<K, V> n = lookup(key);
        if (n != null && n.before != null) {
            return n.before.value;
        }
        return null;
    }

    public V next(K key) {
        Node<K, V> n = lookup(key);
        if (n != null && n.after != null) {
            return n.after.value;
        }
        return null;
    }
}
