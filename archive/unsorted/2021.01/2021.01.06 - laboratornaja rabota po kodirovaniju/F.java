package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class F {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        List<StringBuilder> dict = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            StringBuilder b = new StringBuilder();
            b.append((char) (i + 'a'));
            dict.add(b);
        }
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        StringBuilder res = new StringBuilder();
        StringBuilder cur = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                if (a[i] == dict.size()) {
                    cur.append(dict.get(a[i - 1]).charAt(0));
                } else {
                    cur.append(dict.get(a[i]).charAt(0));
                }
                dict.add(cur);
            }
            res.append(dict.get(a[i]));
            cur = new StringBuilder();
            cur.append(dict.get(a[i]));
        }
        out.println(res);
    }
}
