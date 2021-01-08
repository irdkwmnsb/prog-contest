package main;

public class Sorts {
    @FunctionalInterface
    interface IntComparator {
        int compare(int a, int b);
    }

    private static long mergeSortGetInv(int[] a, int left, int right, IntComparator comparator) {
        if (left + 1 >= right) {
            return 0;
        }
        int m = (right + left) / 2;
        long inv = mergeSortGetInv(a, left, m, comparator) + mergeSortGetInv(a, m, right, comparator);
        int[] newA = new int[right - left];
        int l = left;
        int r = m;
        int i = 0;
        while (l < m && r < right) {
            if (comparator.compare(a[l], a[r]) <= 0) {
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

    public static void sort(int[] a, IntComparator comparator) {
        mergeSortGetInv(a, 0, a.length, comparator);
    }

    private static final int BLOCK_BITS = 16;
    private static final int BLOCK_SIZE = 1 << BLOCK_BITS;

    public static void radixSortUnsigned(int[] a) {
        int[] b = new int[a.length];
        for (int f = 0; f <= 32; f += BLOCK_BITS) {
            int[] c = new int[BLOCK_SIZE];

            for (int l : a) {
                int charAt = l >>> f & (BLOCK_SIZE - 1);
                if (charAt != BLOCK_SIZE - 1)
                    c[charAt + 1]++;
            }

            for (int i = 1; i < BLOCK_SIZE; i++)
                c[i] += c[i - 1];

            for (int l : a) {
                int charAt = l >>> f & (BLOCK_SIZE - 1);
                b[c[charAt]] = l;
                c[charAt]++;
            }

            int[] tmp = a;
            a = b;
            b = tmp;
        }
    }

    static int NUM_DIGITS = 4;
    static long[] pow1000 = new long[NUM_DIGITS];

    static {
        pow1000[0] = 1L;
        for (int i = 1; i < NUM_DIGITS; i++)
            pow1000[i] = pow1000[i - 1] * 1000;
    }

    public static void radixSortFor32Bit(long[] a) {
        long[] b = new long[a.length];
        for (int f = 0; f < NUM_DIGITS; f++) {
            int[] c = new int[1000];

            for (long l : a) {
                int charAt = (int) (l / pow1000[f] % 1000);
                if (charAt != 999)
                    c[charAt + 1]++;
            }

            for (int i = 1; i < 1000; i++)
                c[i] += c[i - 1];

            for (long l : a) {
                int charAt = (int) (l / pow1000[f] % 1000);
                b[c[charAt]] = l;
                c[charAt]++;
            }

            Object tmp = a;
            a = b;
            b = (long[]) tmp;
        }
    }
}
