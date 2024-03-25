package main;

import main.producing.Polynomial;
import main.producing.RecurringPolynomial;

import java.io.PrintWriter;

public class C {
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
        RecurringPolynomial p = new RecurringPolynomial(in.readIntArray(n), in.readIntArray(n));
        print(p.getP());
        print(p.getQ());
    }
}
