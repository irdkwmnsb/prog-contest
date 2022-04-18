package main;

import main.Scanner;
import main.graphs.UnOrientedEdgeListGraph;

import java.io.PrintWriter;

public class H {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        UnOrientedEdgeListGraph g = UnOrientedEdgeListGraph.readFromEdgeList(in);
        Polynomial p = g.getCharacteristicPolynomial();
        out.println(p.size());
        for (int i = p.size(); i >= 0; i--) {
            out.print(p.get(i));
            out.print(" ");
        }
        out.println();
    }
}
