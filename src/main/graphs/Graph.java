package main.graphs;

import java.util.List;

public interface Graph<E extends Edge> {
    List<E> getChildren(int v);

    void addEdge(E edge);

    int getN();

    int getM();
}
