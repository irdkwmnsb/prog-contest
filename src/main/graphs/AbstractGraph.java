package main.graphs;

import main.IntList;

import java.util.LinkedList;
import java.util.Queue;

abstract public class AbstractGraph<E extends Edge> implements Graph<E> {
    public boolean isTree() {
        return !getOriented() && getM() == (getN() - 1) * 2;
    }

    protected void checkJointTree() {
        if (!isTree()) {
            throw new UnsupportedOperationException("Graph is not a tree");
        }
    }

    private void dfsPrufer(int[] parents, int v, int p) {
        for (Edge e : getChildren(v)) {
            if (e.getTo() != p) {
                parents[e.getTo()] = v;
                dfsPrufer(parents, e.getTo(), v);
            }
        }
    }

    public int[] getPruferCode() {
        checkJointTree();
        int[] parents = new int[getN()];
        parents[getN() - 1] = -1;
        dfsPrufer(parents, getN() - 1, -1);
        int[] degree = new int[getN()];
        int ptr = -1;
        for (int i = 0; i < getN(); i++) {
            degree[i] = getChildren(i).size();
            if (degree[i] == 1 && ptr == -1) {
                ptr = i;
            }
        }
        IntList ans = new IntList();
        int leaf = ptr;
        for (int i = 0; i < getN() - 2; i++) {
            int next = parents[leaf];
            ans.push(next + 1);
            degree[next]--;
            if (degree[next] == 1 && next < ptr) {
                leaf = next;
            } else {
                ptr++;
                while (ptr < getN() && degree[ptr] != 1)
                    ptr++;
                leaf = ptr;
            }
        }
        return ans.asArray();
    }
}
