package main;

import main.Scanner;
import java.io.PrintWriter;

public class B {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        for(int i = -710*25000; n>0; n--, i+=710) {
            out.println(i);
        }
    }
}
