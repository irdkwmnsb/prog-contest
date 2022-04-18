package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

public class BBistriiPoiskPodstrokiVStroke {
    PrintWriter out;

    HashUtils hasher = new HashUtils(65537, 1919041867, IntUnaryOperator.identity());
//    HashUtils hasher = new HashUtils(3, 1919041867, (int cc) -> cc - 'a'+1);

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        String p = in.nextWord();
        String t = in.nextWord();
        int n = Math.max(p.length(), t.length());
        long[] hashP = hasher.calcHash(p);
        long[] hashT = hasher.calcHash(t);
        IntList ans = new IntList();
        for(int i = 0; i <= t.length() - p.length(); i++) {
            if(hasher.compareTwoStrings(hashP, 0, hashT, i, p.length())) {
                ans.push(i + 1);
            }
        }
        out.println(ans.length());
        for(int i = 0; i<ans.length(); i++) {
            out.print(ans.get(i));
            out.print(" ");
        }
    }
}
