package main;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

import java.awt.*;
import java.util.stream.Stream;

public class AssignmentCheck implements Checker {
    public AssignmentCheck(String parameters) {

    }

    int calcSumAssignment(String assignment, int[][] mat) {
        Scanner in = new Scanner(assignment);
        int c = in.nextInt();
        int n = mat.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(), y = in.nextInt();
            sum += mat[x - 1][y - 1];
        }
        return sum;
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        Scanner in = new Scanner(input);
        int n = in.nextInt();
        int[][] a = Stream.generate(() -> in.readIntArray(n)).limit(n).toArray(int[][]::new);
        int correct = calcSumAssignment(expectedOutput, a);
        int actual = calcSumAssignment(actualOutput, a);
        if(correct != actual) {
            return Verdict.WA;
        } else {
            return Verdict.OK;
        }
    }
}
