package main;

import java.io.PrintWriter;
import java.util.*;

public class S {
    PrintWriter out;

    static class MyNumber {
        final int ind;
        final int val;

        public MyNumber(int ind, int val) {
            this.ind = ind;
            this.val = val;
        }

        public MyNumber advance(int i, int n) {
            return new MyNumber(ind + i, (val * 10 + i) % n);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyNumber)) return false;
            MyNumber myNumber = (MyNumber) o;
            return ind == myNumber.ind && val == myNumber.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ind, val);
        }

        @Override
        public String toString() {
            return "MyNumber{" +
                    "ind=" + ind +
                    ", val=" + val +
                    '}';
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        Queue<MyNumber> q = new ArrayDeque<>();
        boolean[][] been = new boolean[n + 1][n + 1];
//        Set<MyNumber> been = new HashSet<>();
        MyNumber[][] p = new MyNumber[n + 1][n + 1];
//        Map<MyNumber, MyNumber> p = new HashMap<>();
        MyNumber startNum = new MyNumber(0, 0);
        q.add(startNum);
        been[startNum.ind][startNum.val] = true;
//        been.add(startNum);
        MyNumber endNum = new MyNumber(n, 0);
        while (true) {
            MyNumber num = q.poll();
            if (num == null || num.equals(endNum))
                break;
            for (int i = 0; i < 10; i++) {
                MyNumber newNum = num.advance(i, n);
                if (newNum.ind <= n && !been[newNum.ind][newNum.val]) {
//                    been.add(newNum);
                    been[newNum.ind][newNum.val] = true;
                    q.add(newNum);
//                    p.put(newNum, num);
                    p[newNum.ind][newNum.val] = num;
                }
            }
        }
        IntList ans = new IntList();
        while (endNum != startNum) {
//            MyNumber newNum = p.get(endNum);
            MyNumber newNum = p[endNum.ind][endNum.val];
            ans.push(endNum.ind - newNum.ind);
            endNum = newNum;
        }
        for (int i = ans.length() - 1; i >= 0; i--) {
            out.print(ans.get(i));
        }
    }
}
