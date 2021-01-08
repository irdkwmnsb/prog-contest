package main;

import main.Scanner;

import java.io.PrintWriter;

public class T1 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < (1 << n); i++) {
            out.println(String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0'));
        }
    }
}
