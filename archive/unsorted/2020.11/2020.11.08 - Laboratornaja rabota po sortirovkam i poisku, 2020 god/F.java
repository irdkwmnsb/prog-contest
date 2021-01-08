package main;

import main.Scanner;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class F {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = IntStream.range(1, n+1).toArray();
        for(int i = 0; i<n; i++) {
            int c = a[i];
            a[i] = a[i/2];
            a[i/2] = c;
        }
        out.println(IntStream.of(a).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
