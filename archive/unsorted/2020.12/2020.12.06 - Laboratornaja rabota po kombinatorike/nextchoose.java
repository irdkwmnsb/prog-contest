package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class nextchoose {
    PrintWriter out;

    int[] nextCombination(int[] a, int n, int k) {
        int[] b = Arrays.copyOf(a, k + 1);
        b[k] = n + 1;
        int i = k - 1;
        while ((i >= 0) && (b[i + 1] - b[i] < 2))
            i--;
        if (i >= 0) {
            b[i]++;
            for (int j = i + 1; j <= k - 1; j++)
                b[j] = b[j - 1] + 1;
            return Arrays.copyOf(b, k);
        } else {
            return null;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = IntStream.generate(in::nextInt).limit(k).toArray();
        int[] next = nextCombination(a, n, k);
        if (next != null)
            out.println(Arrays.stream(next).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        else
            out.println(-1);
    }
}
