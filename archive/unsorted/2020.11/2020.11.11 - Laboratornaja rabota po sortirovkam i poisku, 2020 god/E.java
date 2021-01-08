package main;

import main.Scanner;

import java.io.PrintWriter;

public class E {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextWord();
        }
        String[] b = new String[n];
        for (int f = m - 1; f >= (m - k); f--) {
            int[] c = new int[26];

            for (int i = 0; i < n; i++) {
                if (a[i].charAt(f) != 'z')
                    c[a[i].charAt(f) - 'a' + 1]++;
            }

            for (int i = 1; i < 26; i++)
                c[i] += c[i - 1];

            for (int i = 0; i < n; i++) {
                b[c[a[i].charAt(f) - 'a']] = a[i];
                c[a[i].charAt(f) - 'a']++;
            }

            Object tmp = a;
            a = b;
            b = (String[]) tmp;
        }
        out.println(String.join("\n", a));
    }
}