package main;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.tester.State;
import java.io.InputStream;
import java.io.OutputStream;

public class BInteractor {
    public Verdict interact(InputStream input, InputStream solutionOutput, OutputStream solutionInput, State<Boolean> state) {
        return Verdict.UNDECIDED;
    }
}
