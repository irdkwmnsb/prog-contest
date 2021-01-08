package main;

import main.Scanner;

import java.io.PrintWriter;

public class num2brackets2 {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        long k = in.nextLong() + 1;

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
        StringBuilder ans = new StringBuilder();
        int bal = 0;
        long passed;
        for (int i = 2 * n - 1; i >= 0; i--) {
            passed = bal < n ? kt[i][bal + 1] << (i - bal - 1) / 2 : 0;
            if (passed >= k) {
                st.append('(');
                ans.append('(');
                bal++;
            } else {
                k -= passed;
                passed = st.length() > 0 && bal > 0 &&
                        st.charAt(st.length() - 1) == '(' ? kt[i][bal - 1] << (i - bal + 1) / 2 : 0;
                if (passed >= k) {
                    st.deleteCharAt(st.length() - 1);
                    ans.append(')');
                    bal--;
                } else {
                    k -= passed;
                    passed = bal < n ? kt[i][bal + 1] << (i - bal - 1) / 2 : 0;
                    if (passed >= k) {
                        st.append('[');
                        ans.append('[');
                        bal++;
                    } else {
                        k -= passed;
                        ans.append(']');
                        st.deleteCharAt(st.length() - 1);
                        bal--;
                    }
                }
            }
        }
        out.println(ans);
    }
}
