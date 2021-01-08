package main;

import net.egork.chelper.tester.Interactor;

import java.util.InputMismatchException;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class Utils {
    static long[] FACTORIALS = new long[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};

    public static long factorial(int a) {
        if (a < FACTORIALS.length) {
            return FACTORIALS[a];
        } else {
            throw new ArithmeticException("fact(" + a + ") result is too big for a long");
        }
    }

    static int upperBound(int[] a, int x) {
        int l = 0;
        int r = a.length;
        while (r - l != 1) {
            int m = (l + r) / 2;
            if (a[m] > x) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    static int lowerBound(int[] a, int x) {
        int l = -1;
        int r = a.length - 1;
        while (r - l != 1) {
            int m = (l + r) / 2;
            if (a[m] < x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    static int binpow(int a, int b, int m) {
        int res = 1;
        while (b != 0)
            if ((b & 1) != 0) {
                res = (res * a) % m;
                --b;
            } else {
                a *= a;
                b >>= 1;
            }
        return res;
    }

    static IntList divs(int n) {
        IntList ans = new IntList();
        IntList ans2 = new IntList();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                ans.push(i);
                if (n / i != i) {
                    ans2.push(n / i);
                }
            }
        }
        for (int j = 0; j < ans2.length(); j++) {
            ans.push(ans2.get(j));
        }
        return ans;
    }

    public static int findOrderStatistic(int[] array, int k) {
        int left = 0;
        int right = array.length;
        while (true) {
            PartitionResult stats = partition(array, left, right);
            int midL = stats.getMidL();
            int midR = stats.getMidR();
            if (midL <= k && k < midR) {
                return array[midL];
            } else if (k < midL) {
                right = midL;
            } else {
                left = midR;
            }
        }
    }

    public static void reverse(int[] a, int from, int to) {
        while (from < to) {
            swap(from++, to--, a);
        }
    }

    private static class PartitionResult {
        private final int midL;
        private final int midR;

        public PartitionResult(int midL, int midR) {
            this.midL = midL;
            this.midR = midR;
        }

        public int getMidL() {
            return midL;
        }

        public int getMidR() {
            return midR;
        }
    }

    private static PartitionResult partition(int[] array, int left, int right) {
        int low = left, mid = left, high = right - 1;
        int median = array[(low + high) / 2];
        while (mid <= high) {
            if (array[mid] == median) {
                mid++;
            } else if (array[mid] < median) {
                int c = array[low];
                array[low] = array[mid];
                array[mid] = c;
                mid++;
                low++;
            } else {
                int c = array[high];
                array[high] = array[mid];
                array[mid] = c;
                high--;
            }
        }
        return new PartitionResult(low, mid);
    }

    @FunctionalInterface
    public interface DoubleChecker {
        boolean matches(double a);
    }

    private static OptionalDouble doubleBound(double left, double right, DoubleChecker checker, boolean upper) {
        boolean wasMatching = false;
        for (int i = 0; i < 64; i++) {
            double mid = (right + left) / 2;
            if (checker.matches(mid) ^ upper) {
                right = mid;
            } else {
                wasMatching = true;
                left = mid;
            }
        }
        if (wasMatching)
            return OptionalDouble.of(left);
        else
            return OptionalDouble.empty();
    }

    public static OptionalDouble upperBound(double left, double right, DoubleChecker checker) {
        return doubleBound(left, right, checker, true);
    }

    public static OptionalDouble lowerBound(double left, double right, DoubleChecker checker) {
        return doubleBound(left, right, checker, false);
    }

    @FunctionalInterface
    public interface IntChecker {
        boolean matches(int a);
    }

    private static OptionalInt intBound(int left, int right, IntChecker checker, boolean upper) {
        while (right - left != 1) {
            int mid = (right + left) / 2;
            if (checker.matches(mid) ^ upper) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (checker.matches(upper ? left : right))
            return OptionalInt.of(upper ? left : right);
        else
            return OptionalInt.empty();
    }

    public static OptionalInt upperBound(int left, int right, IntChecker checker) {
        return intBound(left, right, checker, true);
    }

    public static OptionalInt lowerBound(int left, int right, IntChecker checker) {
        return intBound(left, right, checker, false);
    }

    @FunctionalInterface
    public interface LongChecker {
        boolean matches(long a);
    }

    private static OptionalLong longBound(long left, long right, LongChecker checker, boolean upper) {
        while (right - left != 1) {
            long mid = (right + left) / 2;
            if (checker.matches(mid) ^ upper) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (checker.matches(upper ? left : right))
            return OptionalLong.of(upper ? left : right);
        else
            return OptionalLong.empty();
    }

    public static OptionalLong upperBound(long left, long right, LongChecker checker) {
        return longBound(left, right, checker, true);
    }

    public static OptionalLong lowerBound(long left, long right, LongChecker checker) {
        return longBound(left, right, checker, false);
    }

    public static void swap(Object[] arr, int x, int y) {
        Object a = arr[x];
        arr[x] = arr[y];
        arr[y] = a;
    }

    @FunctionalInterface
    public interface LongComparator {
        int compare(long a);
    }

    public static OptionalLong findInSorted(long left, long right, LongComparator cmp) {
        while (left + 1 < right) {
            long mid = (right + left) / 2;
            int res = cmp.compare(mid);
            if (res == 0) {
                return OptionalLong.of(mid);
            } else if (res > 0) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return OptionalLong.empty();
    }

    static void swap(int a, int b, int[] c) {
        int temp = c[a];
        c[a] = c[b];
        c[b] = temp;
    }
}
