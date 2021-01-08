package main;

import java.io.PrintWriter;
import java.util.OptionalLong;

public class K {
    private static class KProtocol {
        final Scanner in;
        final PrintWriter out;

        private KProtocol(Scanner in, PrintWriter out) {
            this.in = in;
            this.out = out;
        }

        public long ask(long v) {
            out.printf("? %d\n", v);
            System.err.printf("? %d\n", v);
            out.flush();
            return in.nextLong();
        }

        public void ans(long v) {
            out.printf("! %d\n", v);
            System.err.printf("! %d\n", v);
            out.flush();
        }
    }

    static long MOD = 1000000000000000000L;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        KProtocol proto = new KProtocol(in, out);
        long x = in.nextLong();
        long m = in.nextLong();
        long f = proto.ask(1);
        long near = 1 + (x - f + MOD) % (MOD);
//        1 2 3 4 5
//        1 2 3 5 6 7 8 9

//        3
//        1  2  3  4  5  6  7  8  9  10 11
//        10 12 13 16 17 18 19 20 21 22 23
        // 4 5 6 7 8 9 1 2 3 4 5 6 7 8
        //             4
        System.err.printf("near = %d%n\n", near);
        OptionalLong ans = Utils.findInSorted(near - m - 1, near + 2, (long v) -> {
            long ret = proto.ask((v - 1 + MOD - m) % (MOD - m) + 1);
            if (ret > x + 10000)
                ret -= MOD;
            if (ret < x - 10000)
                ret += MOD;
            if (ret == x)
                return 0;
            return ret > x ? 1 : -1;
        });
        if (ans.isPresent())
            proto.ans((ans.getAsLong() - 1 + MOD - m) % (MOD - m) + 1);
        else
            proto.ans(-1);
    }
}
