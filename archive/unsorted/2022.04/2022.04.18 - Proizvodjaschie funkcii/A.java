package main;

import main.Scanner;
import main.producing.*;

import java.io.PrintWriter;

public class A {
    PrintWriter out;
    public void print(Polynomial p) {
        out.println(p.degree());
        print(p, p.degree());
    }
    public void print(Polynomial p, int n) {
        for(int i = 0; i<=n; i++) {
            if(i != 0)
                out.print(" ");
            out.print(p.get(i));
        }
        out.println();
    }
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        int m = in.nextInt();
        Polynomial a = new SimplePolynomial(in.readIntArray(n + 1));
        Polynomial b = new SimplePolynomial(in.readIntArray(m + 1));
        Polynomial sum = a.add(b);
        print(sum);
        Polynomial mul = a.mul(b);
        print(mul);
        Polynomial div = a.div(b);
        print(div, 999);
    }
}
