package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.stream.IntStream;

public class telemetry {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] cur = new int[n];
        int[] coeff = IntStream.generate(() -> 1).limit(n).toArray();
        do {
            for (int num : cur)
                out.print(num);
            out.println();
            for (int i = 0; i < n; i++) {
                cur[i] += coeff[i];
                if (cur[i] == k || cur[i] == -1) {
                    cur[i] -= coeff[i];
                    coeff[i] = -coeff[i];
                } else {
                    break;
                }
            }
        } while (coeff[n - 1] == 1);
    }
}
