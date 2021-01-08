package main;


import main.Scanner;

import java.io.PrintWriter;

public class E {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        Trie<Integer> root = new Trie<Integer>();
        int c = 26;
        for (int i = 0; i < 26; i++) {
            root.add(String.valueOf((char) (i + 'a')), i);
        }
        Trie<Integer> cur = root;
        String word = in.nextWord();
        for (char i : word.toCharArray()) {
            Trie<Integer> new_cur = cur.walk(i);
            if (new_cur == null) {
                out.print(cur.value);
                out.print(" ");
                cur.add(i, c);
                cur = root.walk(i);
                c++;
            } else {
                cur = new_cur;
            }
        }
        out.println(cur.value);
    }
}
