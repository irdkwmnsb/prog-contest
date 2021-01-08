package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class nextsetpartition {
    PrintWriter out;

    List<List<Integer>> nextPartition(List<List<Integer>> a) {
        List<List<Integer>> b = new ArrayList<>();
        for (List<Integer> ai : a) {
            b.add(new ArrayList<>(ai));
        }
        SortedSet<Integer> used = new TreeSet<>();
        outer:
        for (int i = b.size() - 1; i >= 0; i--) {
            List<Integer> curRow = b.get(i);
            if (!used.isEmpty() && used.last() > curRow.get(curRow.size() - 1)) { // можем добавить в конец минимальный
                Integer last = curRow.get(curRow.size() - 1);
                Integer m = used.stream().filter((j) -> j > last).min(Integer::compareTo).orElseThrow();
                curRow.add(m);
                used.remove(m);
                break;
            }
            for (int j = curRow.size() - 1; j >= 0; j--) { // Можем перезаписать
                if (j > 0 && !used.isEmpty() && used.last() > curRow.get(j)) {
                    Integer old = curRow.get(j);
                    Integer m = used.stream().filter((k) -> k > old).min(Integer::compareTo).orElseThrow();
                    curRow.set(j, m);
                    used.remove(m);
                    used.add(old);
                    break outer;
                }
                used.add(curRow.remove(curRow.size() - 1));
                if (curRow.isEmpty()) {
                    b.remove(i);
                }
            }
        }
        for (Integer left : used) {
            b.add(List.of(left));
        }
        return b;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        while (true) {
            int n = in.nextInt(), k = in.nextInt();
            if (n == 0 && k == 0)
                break;
            List<List<Integer>> a = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                List<Integer> set = Arrays.stream(in.nextLine().split("\\s")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
                a.add(set);
            }
            List<List<Integer>> b = nextPartition(a);
            out.println(n + " " + b.size());
            for (List<Integer> i : b) {
                out.println(i.stream().mapToInt((l) -> l).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
            }
            out.println();
        }
    }
}
