package main;



import net.egork.chelper.tester.Verdict;
import net.egork.chelper.checkers.Checker;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OChecker implements Checker {
    public OChecker(String parameters) {
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
        Scanner in = new Scanner(input);
        Scanner ans = new Scanner(actualOutput);
        for (int n = in.nextInt(); n != 0; n = in.nextInt()) {
            int[] a = in.readIntArray(n);
            int wrongMask = Integer.parseInt(IntStream.of(a).mapToObj(String::valueOf).collect(Collectors.joining("")), 2);
            int m = ans.nextInt();
            if (m == -1)
                continue;
            Comparator[] comps = new Comparator[m];
            for (int i = 0; i < m; i++) {
                comps[i] = new Comparator(ans.nextInt() - 1, ans.nextInt() - 1);
            }
            boolean failsMask = false;
            for (int mask = 1; mask < (1 << n); mask++) {
                int maskCopy = mask;
                for (var comp : comps) {
                    maskCopy = comp.compare(maskCopy);
                }
                if (maskCopy != (1 << Integer.bitCount(mask)) - 1) {
                    if (mask != wrongMask) {
                        return new Verdict(Verdict.VerdictType.WA, "Mask " + Integer.toBinaryString(mask) + " results in " + Integer.toBinaryString(maskCopy));
                    } else {
                        failsMask = true;
                    }
                }
            }
            if (!failsMask) {
                return new Verdict(Verdict.VerdictType.WA, "Is sorting");
            }
        }
        return Verdict.OK;
    }
}
