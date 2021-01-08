package main;

import main.Scanner;

import java.io.PrintWriter;

public class A {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int[] a = in.readIntArray(in.nextInt());
        boolean heap = true;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[(i-1) / 2]) {
                heap = false;
                break;
            }
        }
        out.println(heap ? "YES" : "NO");
    }
}
