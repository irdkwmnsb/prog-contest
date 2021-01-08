package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class D {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        List<Character> alph = new ArrayList<>(26);
        for (int i = 0; i < 26; i++) {
            alph.add((char) ('a' + i));
        }
        String s = in.nextWord();
        for (char i : s.toCharArray()) {
            out.print(alph.indexOf(i) + 1);
            out.print(" ");
            alph.remove((Character) i);
            alph.add(0, i);
        }
    }
}
