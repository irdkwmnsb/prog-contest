package main;

import main.stree.IntSTree;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class A {
    PrintWriter out;

    private static class Task {
        int d;
        long w;
        public static Comparator<Task> comparator = Comparator.comparing((Task t) -> t.w);

        public Task(int d, long w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "d=" + d +
                    ", w=" + w +
                    '}';
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        List<Task> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(new Task(in.nextInt(), in.nextLong()));
        }
        a.sort(Task.comparator);
        Utils.reverseList(a, 0, a.size() - 1);
        IntSTree tree = new IntSTree(n, Integer::max, null, -1);
        for (int i = 0; i < n; i++) {
            tree.set(i, i);
        }
        long maxP = 0;
        for(Task t: a) {
            int z = tree.get(0, Math.min(n, t.d - 1));
            if(z != -1) {
                tree.set(z, -1);
            } else {
                maxP += t.w;
            }
        }
        out.println(maxP);
    }
}
