package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class nextperm {
    PrintWriter out;

    int[] nextPerm(int[] a) {
        int n = a.length;
        int[] next = Arrays.copyOf(a, n);
        for (int i = n - 2; i >= 0; i--) {
            if (next[i] < next[i + 1]) {
                int min = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if (next[j] < next[min] && (next[j] > next[i])) {
                        min = j;
                    }
                }
                Utils.swap(i, min, next);
                Utils.reverse(next, i + 1, n - 1);
                return next;
            }
        }
        return new int[n];
    }

    int[] prevPerm(int[] a) {
        int n = a.length;
        int[] prev = Arrays.copyOf(a, n);
        for (int i = n - 2; i >= 0; i--) {
            if (prev[i] > prev[i + 1]) {
                int max = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if (prev[j] > prev[max] && (prev[j] < prev[i])) {
                        max = j;
                    }
                }
                Utils.swap(i, max, prev);
                Utils.reverse(prev, i + 1, n - 1);
                return prev;
            }
        }
        return new int[n];
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] arr = IntStream.generate(in::nextInt).limit(n).toArray();
        out.println(Arrays.stream(prevPerm(arr)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        out.println(Arrays.stream(nextPerm(arr)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
