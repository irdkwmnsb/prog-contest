package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class nextmultiperm {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        int i = n - 2;
        while ((i >= 0) && (a[i] >= a[i + 1]))
            i--;
        if (i >= 0) {
            int j = i + 1;
            while ((j < n - 1) && (a[j + 1] > a[i]))
                j++;
            Utils.swap(i, j, a);
            Utils.reverse(a, i + 1, n - 1);
            out.println(IntStream.of(a).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        } else {
            out.println(IntStream.generate(() -> 0).limit(n).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
        }
    }
}
