package main;


import java.util.HashMap;
import java.util.Map;

public class Trie<T> {
    T value;
    Map<Character, Trie<T>> c = new HashMap<>();

    private void add(String a, int i, T value) {
        if (i == a.length()) {
            this.value = value;
            return;
        }
        c.putIfAbsent(a.charAt(i), new Trie<>());
        c.compute(a.charAt(i), (k, v) -> {
            assert v != null;
            v.add(a, i + 1, value);
            return v;
        });
    }

    /**
     * @param a string to add to trie
     * @param value value associated with that trie
     */
    public void add(String a, T value) {
        add(a, 0, value);
    }

    /**
     * @param a single character string to add to trie
     * @param value value to add
     */
    public void add(char a, T value) {
        add(String.valueOf(a), value);
    }

    /**
     *
     * @param a where to walk
     * @return new Trie for the character
     */
    public Trie<T> walk(char a) {
        return c.get(a);
    }

    private T get(String a, int i) {
        if (i == a.length()) {
            return value;
        }
        if (!c.containsKey(a.charAt(i))) {
            return null;
        }
        return c.get(a.charAt(i)).get(a, i);
    }

    /**
     *
     * @param a string to find
     * @return object associated with that string
     */
    public T get(String a) {
        return get(a, 0);
    }
}
