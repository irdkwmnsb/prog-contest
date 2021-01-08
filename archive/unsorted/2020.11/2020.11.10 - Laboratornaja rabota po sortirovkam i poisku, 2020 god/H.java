package main;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class H {
    long mergeSortGetInv(int[] a, int left, int right) {
        if (left + 1 >= right) {
            return 0;
        }
        int m = (right + left) / 2;
        long inv = mergeSortGetInv(a, left, m) + mergeSortGetInv(a, m, right);
        int[] newA = new int[right - left];
        int l = left;
        int r = m;
        int i = 0;
        while (l < m && r < right) {
            if (a[l] <= a[r]) {
                newA[i++] = a[l++];
            } else {
                newA[i++] = a[r++];
                inv += m - l;
            }
        }
        while (l < m) {
            newA[i++] = a[l++];
        }
        while (r < right) {
            newA[i++] = a[r++];
        }
        System.arraycopy(newA, 0, a, left, newA.length);
        return inv;
    }

    long countInv(int[] a) {
        return mergeSortGetInv(a, 0, a.length);
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        final int m = in.nextInt(), a = in.nextInt(), b = in.nextInt();
        int[] arr = IntStream.generate(new IntSupplier() {
            int cur = 0;


            public int getAsInt() {
                cur = cur * a + b;
                return cur >>> 8;
            }
        }).map((i) -> Integer.remainderUnsigned(i, m)).limit(n).toArray();
        out.println(countInv(arr));
    }
}
