package uselessshit;

public class IntSet {
    private static class Node {
        Node zero = null;
        Node one = null;
        boolean isTerm = false;
    }

    Node root = new Node();
    int numItems = 0;

    void add(int add) {
        numItems++;
        Node cur = root;
        while (add != 0) {
            if ((add & 1) == 0) {
                if (cur.zero == null) {
                    cur.zero = new Node();
                }
                cur = cur.zero;
            } else {
                if (cur.one == null) {
                    cur.one = new Node();
                }
                cur = cur.one;
            }
        }
    }

    void addItem(Node cur, int i) {

    }

    int[] items() {

    }
}
