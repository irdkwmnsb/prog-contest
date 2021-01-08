package main;

import main.Scanner;
import java.io.PrintWriter;

public class A {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int a = in.nextInt(), b = in.nextInt(), n = in.nextInt();
        out.println(2 * ((n - b) / (b - a) + ((n - b) % (b - a) == 0 ? 0 : 1)) + 1);
    }
}
