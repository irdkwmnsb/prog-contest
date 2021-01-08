package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;

public class antigray {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < Utils.binpow(3, n - 1, Integer.MAX_VALUE); i++) {
            int[] vec = String.format("%" + n + "s", Integer.toString(i, 3)).replace(' ', '0').chars().map((c) -> c - '0').toArray();
            for (int j = 0; j < 3; j++) {
                for (int c : vec) {
                    out.print(c);
                }
                out.println();
                for (int k = 0; k < vec.length; k++) {
                    vec[k] = (vec[k] + 1) % 3;
                }
            }
        }
    }
}
