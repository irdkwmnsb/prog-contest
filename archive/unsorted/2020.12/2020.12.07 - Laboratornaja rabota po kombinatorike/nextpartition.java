package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class nextpartition {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        in.nextChar();
        IntList b = new IntList(Arrays.stream(in.nextWord().split("\\+")).mapToInt(Integer::parseInt).toArray());
        if (b.length() == 1) {
            out.println("No solution");
        } else {
            b.set(b.length() - 1, b.get(b.length() - 1) - 1);
            b.set(b.length() - 2, b.get(b.length() - 2) + 1);
            if (b.get(b.length() - 2) > b.get(b.length() - 1)) {
                b.set(b.length() - 2, b.get(b.length() - 1) + b.get(b.length() - 2));
                b.pop();
            } else {
                while (b.get(b.length() - 2) * 2 <= b.get(b.length() - 1)) {
                    b.push(b.get(b.length() - 1) - b.get(b.length() - 2));
                    b.set(b.length() - 2, b.get(b.length() - 3));
                }
            }
            out.print(n + "=");
            out.println(IntStream.of(b.asArray()).mapToObj(Integer::toString).collect(Collectors.joining("+")));
        }
    }
}
