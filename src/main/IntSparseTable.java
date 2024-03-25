package main;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

public class IntSparseTable {
    int[][] st;
    IntBinaryOperator func;

    public IntSparseTable(int[] arr, IntBinaryOperator func) {
        this.func = func;
        int n = arr.length;
        int log2n = Integer.bitCount((Integer.highestOneBit(n) << 1) - 1);
        st = new int[log2n][n];
        st[0] = Arrays.copyOf(arr, n);
        for (int j = 1; j < log2n; j++) {
            for (int i = 0; i < n; i++) {
                st[j][i] = st[j - 1][i];
                if (i + (1 << (j - 1)) < n)
                    st[j][i] = func.applyAsInt(st[j][i], st[j - 1][i + (1 << (j - 1))]);
            }
        }
    }

    public int getInterval(int l, int r) {
        if (l > r) {
            int c = r;
            r = l;
            l = c;
        }
        int len = r - l + 1;
        int log2n = Integer.bitCount(Integer.highestOneBit(len) - 1);
        int left = st[log2n][l];
        int right = st[log2n][(int) (r - MathUtils.binpow(2, log2n) + 1)];
        return func.applyAsInt(left, right);
    }
}
