package main;

import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

public class NChecker implements Checker {
    public NChecker(String parameters) {
    }

    static class Comparator {
        final int x, y;

        Comparator(int x, int y) {
            this.x = Integer.min(x, y);
            this.y = Integer.max(x, y);
        }

        int compare(int in) {
            if ((in & (1 << x)) == 0 && (in & (1 << y)) != 0) {
                in ^= (1 << x);
                in ^= (1 << y);
            }
            return in;
        }
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        Scanner in = new Scanner(actualOutput);
        int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
        Comparator[][] comps = new Comparator[k][];
        for (int i = 0; i < k; i++) {
            m = in.nextInt();
            comps[i] = new Comparator[m];
            for (int j = 0; j < m; j++) {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                comps[i][j] = new Comparator(x, y);
            }
        }
        for (int mask = 1; mask < (1 << n); mask++) {
            int maskCopy = mask;
            for (var layer : comps) {
                for (var comp : layer) {
                    maskCopy = comp.compare(maskCopy);
                }
            }
//            out.println(String.format("%s -> %s",
//                    Integer.toBinaryString(mask),
//                    Integer.toBinaryString(maskCopy)));
            if (maskCopy != (1 << Integer.bitCount(mask)) - 1) {
                return new Verdict(Verdict.VerdictType.WA, "Mask " + Integer.toBinaryString(mask) + " results in " + Integer.toBinaryString(maskCopy));
            }
        }
        return Verdict.OK;
    }
}
