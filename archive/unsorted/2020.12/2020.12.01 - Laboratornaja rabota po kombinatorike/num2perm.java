package main;

import java.io.PrintWriter;
import java.util.Arrays;

public class num2perm {
    int[] permFromNumber(int n, long k) {
        int[] perm = new int[n];
        Arrays.fill(perm, -1);
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            long f = Utils.factorial(n - i - 1);
            long passed = k / f;
            k %= f;
            int curFree = 0;
            for (int j = 0; j < n; j++) {
                if (!used[j]) {
                    curFree++;
                    if (curFree == passed + 1) {
                        perm[i] = j + 1;
                        used[j] = true;
                        break;
                    }
                }
            }
        }
        return perm;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();
        for (int ch : permFromNumber(n, k)) {
            out.print(ch);
            out.print(" ");
        }
        out.println();
    }
}
