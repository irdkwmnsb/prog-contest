package main;

import main.Scanner;

import java.io.PrintWriter;

public class brackets2num2 {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String s = in.nextWord();
        int n = s.length() / 2;

        long[][] kt = new long[2 * n + 1][2 * n + 1];
        kt[0][0] = 1;
        for (int i = 1; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                kt[i][j] = kt[i - 1][j + 1];
                if (j != 0) {
                    kt[i][j] += kt[i - 1][j - 1];
                }
            }
        }
        StringBuilder st = new StringBuilder();
        long ans = 0;
        int bal = 0;
        int t = -1;
        for (int i = 0; i < s.length(); i++) {
            int l = 2 * n - i - 1;
            if (s.charAt(i) == '(') {
                st.append('(');
                bal++;
            } else {
                if (bal < n) {
                    t = bal + 1;
                    ans += kt[l][t] << ((l - t) / 2);
                }
                if (s.charAt(i) == ')') {
                    st.deleteCharAt(st.length() - 1);
                    bal--;
                } else {
                    if (bal > 0 && st.length() > 0 && st.charAt(st.length() - 1) == '(') {
                        t = bal - 1;
                        ans += kt[l][t] << ((l - t) / 2);
                    }
                    if (s.charAt(i) == '[') {
                        st.append('[');
                        bal++;
                    } else {
                        if (bal < n) {
                            ans += kt[l][t] << ((l - t) / 2);
                        }
                        if (s.charAt(i) == ']') {
                            st.deleteCharAt(st.length() - 1);
                            bal--;
                        }
                    }
                }
            }
        }
        out.println(ans);
    }
}
