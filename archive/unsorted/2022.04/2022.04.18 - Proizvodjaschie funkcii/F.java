package main;

import main.Scanner;
import main.producing.Polynomial;
import main.producing.PowPolynomial;
import main.producing.SimplePolynomial;

import javax.xml.crypto.dsig.SignatureMethod;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class F {
    static int MODULO = 1_000_000_007;
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int k = in.nextInt();
        int m = in.nextInt();
        int[] a = in.readIntArray(k);
        int[] ans = new int[m+1];
        ans[0] = 1;
        int[] s = new int[m+1];
        s[0] = 1;
        for(int i = 1; i<=m; i++) {
            for (int j = 0; j < k; j++) {
                if (i >= a[j]) {
                    ans[i] = (int) (((long) s[i - a[j]] + ans[i]) % MODULO);
                }
            }
            for (int j = 0; j <= i; j++) {
                s[i] = (int) (s[i] + (((long) ans[i - j] * ans[j]) % MODULO)) % MODULO;
            }
        }
        out.println(Arrays.stream(ans).skip(1).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }
}
