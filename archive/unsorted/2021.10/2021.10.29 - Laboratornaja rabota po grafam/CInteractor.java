package main;

import main.graphs.Edge;
import main.graphs.MatrixGraph;
import net.egork.chelper.tester.Verdict;
import net.egork.chelper.tester.State;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;

public class CInteractor {
    static int MAX_TESTS = 10000;

    public Verdict interact(InputStream input, InputStream solutionOutput, OutputStream solutionInput, State<Boolean> state) {
        PrintStream solutionPrinter = new PrintStream(solutionInput);
        Scanner solutionScanner = new Scanner(solutionOutput);
        MatrixGraph<Edge> g = MatrixGraph.readOrientedCompleteEdgeListGraph(new Scanner(input));
        solutionPrinter.println(g.getN());
        solutionPrinter.flush();
        for (int test = 0; test < MAX_TESTS; test++) {
            if (!solutionScanner.hasNextInt()) {
                return new Verdict(Verdict.VerdictType.PE, "No request");
            }
            int r_type = solutionScanner.nextInt();
            if (r_type == 0) {
                int[] ans = new int[g.getN()];
                for (int i = 0; i < g.getN(); i++) {
                    if (!solutionScanner.hasNextInt()) {
                        return new Verdict(Verdict.VerdictType.PE, "Expected int in output");
                    }
                    ans[i] = solutionScanner.nextInt() - 1;
                }
                for (int i = 0; i < g.getN() - 1; i++) {
                    int v = ans[i], u = ans[i + 1];
                    if(g.getEdge(v, u) == null) {
                        return new Verdict(Verdict.VerdictType.WA, String.format("No edge from %s to %s", v + 1, u + 1));
                    }
                }
                return Verdict.OK;
            } else {
                if (!solutionScanner.hasNextInt()) {
                    return new Verdict(Verdict.VerdictType.PE, "No v");
                }
                int v = solutionScanner.nextInt() - 1;
                if (!solutionScanner.hasNextInt()) {
                    return new Verdict(Verdict.VerdictType.PE, "No u");
                }
                int u = solutionScanner.nextInt() - 1;
                if (g.getEdge(v, u) == null) {
                    solutionPrinter.println("NO");
                } else {
                    solutionPrinter.println("YES");
                }
                solutionPrinter.flush();
            }
        }
        return new Verdict(Verdict.VerdictType.WA, "Too many requests");
    }
}
