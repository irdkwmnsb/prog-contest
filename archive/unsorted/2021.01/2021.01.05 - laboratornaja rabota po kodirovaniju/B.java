package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;

public class B {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String word = in.nextWord();
        int n = word.length();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            word = word.substring(1) + word.charAt(0);
            a[i] = word;
        }
        Arrays.sort(a);
        for (String i : a) {
            out.print(i.charAt(n - 1));
        }
        out.println();
    }
}
