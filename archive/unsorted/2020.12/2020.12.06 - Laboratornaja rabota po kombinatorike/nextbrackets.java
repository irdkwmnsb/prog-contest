package main;


import main.Scanner;

import java.io.PrintWriter;

public class nextbrackets {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String s = in.nextWord();
        int counter_close = 0;
        int counter_open = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                counter_open++;
                if (counter_close > counter_open) {
                    break;
                }
            } else {
                counter_close++;
            }
        }
        s = s.substring(0, s.length() - counter_open - counter_close);
        if (s.length() == 0) {
            out.println("-");
        } else {
            out.println(s + ')' + "(".repeat(counter_open) + ")".repeat(counter_close - 1));
        }
        ;
    }
}
