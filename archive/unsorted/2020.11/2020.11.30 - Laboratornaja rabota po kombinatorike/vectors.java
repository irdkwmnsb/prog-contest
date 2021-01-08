package main;

import main.Scanner;

import java.io.PrintWriter;

public class vectors {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        IntList ans = new IntList();
        for (int vec = 0; vec < (1 << n); vec++) {
            boolean hasTwoOnes = false;
            for (int shift = 0; shift < n - 1; shift++) {
                if (((vec >> shift) & 0b11) == 0b11) {
                    hasTwoOnes = true;
                    break;
                }
            }
            if (!hasTwoOnes) {
                ans.push(vec);
            }
        }
        out.println(ans.length());
        for (int i = 0; i < ans.length(); i++) {
            int vec = ans.get(i);
            out.println(String.format("%" + n + "s", Integer.toBinaryString(vec)).replace(' ', '0'));
        }
    }
}
