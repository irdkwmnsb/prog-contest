package main;

import java.io.PrintWriter;

public class C {
    static int DELETED = 1000000001, NOT_PUSH = 1000000002;

    private static boolean isCommandChar(char c) {
        return Character.isAlphabetic(c) || c == '-';
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        PriorQueueWithAccess pq = new PriorQueueWithAccess();
        while (in.hasNext(C::isCommandChar)) {
            String c = in.next(C::isCommandChar);
            if (c.charAt(0) == 'p') { // Push
                pq.push(in.nextInt());
            } else  {
                pq.push(NOT_PUSH);
                if (c.charAt(0) == 'e') { // Extract-min
                    if (pq.isEmpty() || pq.getMin().getVal() == DELETED) {
                        out.println("*");
                    } else {
                        PriorQueueWithAccess.Entry min = pq.getMin();
                        out.print(min.getVal());
                        out.print(" ");
                        out.println(min.getInd() + 1);
                        pq.set(min.getInd(), DELETED);
                    }
                } else if(c.charAt(0) == 'd') { // Decrease-key
                    int ind = in.nextInt() - 1;
                    int v = in.nextInt();
                    pq.set(ind, v);
                }
            }
        }
    }
}
