package main;


import main.Scanner;

import java.io.PrintWriter;
import java.util.Random;
import java.util.stream.IntStream;

public class F {
    PrintWriter out;
    String[] strings = new String[]{"Aa", "BB"};
    Random random = new Random();

    String[] sample(String[] a, int k) {
        return IntStream.generate(() -> random.nextInt(a.length)).limit(k).mapToObj((i) -> a[i]).toArray(String[]::new);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int k = in.nextInt();
        for (int i = 0; i < k; i++) {
            out.println(String.join("", sample(strings, 100)));
        }
    }
}
