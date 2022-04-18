package main;

import main.hash.MyIntSet;

import java.io.PrintWriter;

public class A {
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        MyIntSet set = new MyIntSet();
        int i = 0;
        while (in.hasNextWord()) {
            i++;
            String op = in.nextWord();
            switch (op) {
                case "insert":
                    set.insert(in.nextInt());
                    break;
                case "delete":
                    set.remove(in.nextInt());
                    break;
                case "exists":
                    out.println(set.contains(in.nextInt()));
                    break;
            }
            if (i % 100000 == 0) {
                System.gc();
            }
        }
    }
}
