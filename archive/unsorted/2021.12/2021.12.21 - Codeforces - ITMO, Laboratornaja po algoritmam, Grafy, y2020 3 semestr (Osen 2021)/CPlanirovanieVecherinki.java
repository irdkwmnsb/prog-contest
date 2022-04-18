package main;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CPlanirovanieVecherinki {
    PrintWriter out;
    IntList[] g;
    IntList[] gr;
    boolean[] used;
    IntList order = new IntList();
    int[] comp;
    int n;

    void dfs1(int v) {
        used[v] = true;
        for (int i = 0; i < g[v].length(); ++i) {
            int to = g[v].get(i);
            if (!used[to]) {
                dfs1(to);
            }
        }
        order.push(v);
    }

    void dfs2(int v, int cl) {
        comp[v] = cl;
        for (int i = 0; i < gr[v].length(); ++i) {
            int to = gr[v].get(i);
            if (comp[to] == -1) {
                dfs2(to, cl);
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt() * 2;
        int m = in.nextInt();
        g = Stream.generate(IntList::new).limit(n).toArray(IntList[]::new);
        gr = Stream.generate(IntList::new).limit(n).toArray(IntList[]::new);
        used = new boolean[n];
        Map<String, Integer> nameToNumber = new HashMap<>();
        String[] names = new String[n];
        for (int i = 0; i < n / 2; i++) {
            names[i] = in.nextWord();
            nameToNumber.put(names[i], i);
        }
        for (int i = 0; i < m; i++) {
            String from = in.nextWord();
            in.nextWord();
            String to = in.nextWord();
            int v = getNameNumber(nameToNumber, from);
            int u = getNameNumber(nameToNumber, to);
            g[v].push(u);
            gr[u].push(v);
        }

        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                dfs1(i);
            }
        }

        Utils.reverse(order, 0, order.length() - 1);
        comp = IntStream.generate(() -> -1).limit(n).toArray();
        for (int i = 0, j = 0; i < n; ++i) {
            int v = order.get(i);
            if (comp[v] == -1) {
                dfs2(v, j++);
            }
        }

        for (int i = 0; i < n; ++i) {
            if (comp[i] == comp[i ^ 1]) {
                out.println(-1);
                return;
            }
        }
        IntList nums = new IntList();
        for (int i = 0; i < n; ++i) {
            boolean ans = comp[i] < comp[i ^ 1];
            if((i&1) == 0 && ans) {
                nums.push(i/2);
            }
        }
        out.println(nums.length());
        for(int i = 0; i<nums.length(); i++) {
            out.print(names[nums.get(i)]);
            out.print(" ");
        }
        out.println();
    }

    private int getNameNumber(Map<String, Integer> nameToNumber, String from) {
        int fromV = 0;
        fromV += nameToNumber.get(from.substring(1)) * 2;
        if (from.startsWith("+")) {
            fromV += 1;
        }
        return fromV;
    }
}
