package main;

import java.util.Arrays;

import static main.Utils.factorial;

public class Combinatorics {
    static int[] permFromNumber(int n, long k) {
        int[] perm = new int[n];
        Arrays.fill(perm, -1);
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            long f = factorial(n - i - 1);
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

    static long numberFromPerm(int[] perm) {
        long ans = 0;
        int n = perm.length;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < perm[i]; j++) {
                if (!used[j]) {
                    ans += factorial(n - i - 1);
                }
            }
            used[perm[i]] = true;
        }
        return ans;
    }

//    static long C(int n, int k) {
//        return factorial(n) / (factorial(k)) / factorial(n - k);
//    }

    static private final long[][] _C = new long[31][31];

    static {
        _C[0][0] = 1;
        for (int i = 1; i < 31; i++) {
            _C[i][0] = 1;
            for (int j = 1; j < 31; j++) {
                _C[i][j] = _C[i - 1][j - 1] + _C[i - 1][j];
            }
        }
    }

    static public long C(int n, int k) {
        return _C[n][k];
    }

    static int[] combinationFromNumber(int n, int k, long m) {
        int next = 0;
        int[] comb = new int[k];
        int combI = 0;
        while (k > 0) {
            if (m < C(n - 1, k - 1)) {
                comb[combI++] = next;
                k--;
            } else {
                m -= C(n - 1, k - 1);
            }
            n--;
            next++;
        }
        return comb;
    }

    static long numberFromCombination(int n, int k, int[] comb) {
        long ans = 0;
        for (int i = 0; i < k; i++) {
            for (int j = (i == 0 ? 0 : comb[i - 1]) + 1; j < comb[i]; j++) {
                ans += C(n - j, k - i - 1);
            }
        }
        return ans;
    }

    static int[] nextPerm(int[] a) {
        int n = a.length;
        int[] next = Arrays.copyOf(a, n);
        for (int i = n - 2; i >= 0; i--) {
            if (next[i] < next[i + 1]) {
                int min = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if (next[j] < next[min] && (next[j] > next[i])) {
                        min = j;
                    }
                }
                Utils.swap(i, min, next);
                Utils.reverse(next, i + 1, n - 1);
                return next;
            }
        }
        return new int[n];
    }

    static int[] prevPerm(int[] a) {
        int n = a.length;
        int[] prev = Arrays.copyOf(a, n);
        for (int i = n - 2; i >= 0; i--) {
            if (prev[i] > prev[i + 1]) {
                int max = i + 1;
                for (int j = i + 1; j < n; j++) {
                    if (prev[j] > prev[max] && (prev[j] < prev[i])) {
                        max = j;
                    }
                }
                Utils.swap(i, max, prev);
                Utils.reverse(prev, i + 1, n - 1);
                return prev;
            }
        }
        return new int[n];
    }
}
