package main;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class HashUtils {
    final long modulo;
    final long pow;
    final IntUnaryOperator mappingFunction;
    private long[] pows;

    public HashUtils(long pow, long mod, IntUnaryOperator mappingFunction) {
        this.modulo = mod;
        this.pow = pow;
        this.mappingFunction = mappingFunction;
    }

    private void recalcPows(int newLength) {
        int oldLength;
        if (pows == null) {
            oldLength = 0;
            pows = new long[newLength];
            pows[0] = 1;
        } else {
            oldLength = pows.length;
            pows = Arrays.copyOf(pows, newLength);
        }
        for (int i = Math.max(1, oldLength); i < newLength; i++) {
            pows[i] = (pows[i - 1] * pow) % modulo;
        }
    }

    private void assertPowsLength(int newLength) {
        if (pows == null || pows.length < newLength) {
            recalcPows(newLength);
        }
    }

    long[] calcHash(String a) {
        assertPowsLength(a.length());
        long[] h = new long[a.length()];
        for (int i = 0; i < a.length(); i++) {
            if (i > 0) {
                h[i] = h[i - 1];
            }
            int c = this.mappingFunction.applyAsInt(a.charAt(i));
            h[i] = (h[i] + (c * pows[a.length() - i - 1]) % modulo) % modulo;
        }
        return h;
    }

    private long getShiftedHash(long[] h, int start, int end) {
        assertPowsLength(h.length);
        long resH = h[end - 1];
        if (start > 0) {
            resH = (resH + modulo - h[start - 1]) % modulo;
        }
        return resH;
    }

    boolean compareTwoStrings(long[] h1, int start1, long[] h2, int start2, int length) {
        long hash1 = getShiftedHash(h1, start1, start1 + length);
        int powShift1 = h1.length - start1 - length;
        long hash2 = getShiftedHash(h2, start2, start2 + length);
        int powShift2 = h2.length - start2 - length;
        if(powShift1 > powShift2) {
            hash2 = (hash2 * pows[powShift1 - powShift2]) % modulo;
        } else {
            hash1 = (hash1 * pows[powShift2 - powShift1]) % modulo;
        }
        return hash1 == hash2;
    }
}
