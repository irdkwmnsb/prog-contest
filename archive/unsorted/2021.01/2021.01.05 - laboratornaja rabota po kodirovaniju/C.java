package main;

import main.Scanner;

import java.io.PrintWriter;

public class C {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String word = in.nextWord();
        int[] count = new int[26];
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'a']++;
        }
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
            count[i] = sum - count[i];
        }
        int[] t = new int[word.length()];
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            t[count[c]] = i;
            count[c]++;
        }
        int j = t[0];
        for (int i = 0; i < word.length(); i++) {
            out.print(word.charAt(j));
            j = t[j];
        }
        out.println();
    }
}
