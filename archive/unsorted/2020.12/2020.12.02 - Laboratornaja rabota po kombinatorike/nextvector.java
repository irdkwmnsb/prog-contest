package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class nextvector {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int[] vec = in.nextWord().codePoints().map((a) -> a - '0').toArray();
        int n = vec.length;
        if (Arrays.stream(vec).sum() == 0) {
            out.println("-");
        } else {
            int[] prevVec = Arrays.copyOf(vec, n);
            for (int i = n - 1; i >= 0; i--) {
                if (prevVec[i] == 1) {
                    prevVec[i] = 0;
                    for (int j = i + 1; j < n; j++) {
                        prevVec[j] = 1;
                    }
                    break;
                }
            }
            out.println(Arrays.stream(prevVec).mapToObj(String::valueOf).collect(Collectors.joining("")));
        }
        if (Arrays.stream(vec).sum() == n) {
            out.println("-");
        } else {
            int[] nextVec = Arrays.copyOf(vec, n);
            for (int i = n - 1; i >= 0; i--) {
                if (nextVec[i] == 0) {
                    nextVec[i] = 1;
                    for (int j = i + 1; j < n; j++) {
                        nextVec[j] = 0;
                    }
                    break;
                }
            }
            out.println(Arrays.stream(nextVec).mapToObj(String::valueOf).collect(Collectors.joining("")));
        }
    }
}
