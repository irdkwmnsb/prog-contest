package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class A {
    PrintWriter out;

    private static class StatEntry implements Comparable<StatEntry> {
        public int i;
        public long times;

        public StatEntry(int i, long times) {
            this.i = i;
            this.times = times;
        }

        @Override
        public int compareTo(StatEntry o) {
            int res = Long.compare(times, o.times);
            if (res == 0)
                res = Integer.compare(i, o.i);
            return res;
        }

        @Override
        public String toString() {
            return "StatEntry{" +
                    "i=" + i +
                    ", times=" + times +
                    '}';
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int n = in.nextInt();
        long[] w = in.readLongArray(n);
        int[] p = new int[n * 2 - 1];
        NavigableSet<StatEntry> s = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            s.add(new StatEntry(i, w[i]));
        }
        for (int i = n; i < n * 2 - 1; i++) {
            StatEntry a = s.pollFirst();
            StatEntry b = s.pollFirst();
            assert a != null && b != null;
            p[a.i] = i;
            p[b.i] = i;
            s.add(new StatEntry(i, a.times + b.times));
        }
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            while (p[i] != 0) {
                p[i] = p[p[i]];
                d[i]++;
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += d[i] * w[i];
        }
        out.println(ans);
    }
}
