package main;

import main.Scanner;

import java.io.PrintWriter;

public class chaincode {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        boolean[] used = new boolean[(1 << n)];
        int vec = 0;
        while (true) {
            used[vec] = true;
            out.println(String.format("%" + n + "s", Integer.toBinaryString(vec)).replace(' ', '0'));
            int newVec1 = ((vec << 1) | 1) & ((1 << n) - 1);
            if (!used[newVec1]) {
                vec = newVec1;
                continue;
            }
            int newVec0 = (vec << 1) & ((1 << n) - 1);
            if (!used[newVec0]) {
                vec = newVec0;
                continue;
            }
            break;
        }
    }
}
