package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class Pillar {
    public int x, y, h;

    Pillar(Scanner in) {
        x = in.nextInt();
        y = in.nextInt();
        h = in.nextInt();
    }
}

public class I {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Pillar[] pillars = new Pillar[n];
        for (int i = 0; i < n; i++)
            pillars[i] = new Pillar(in);
        int xl = List.of(pillars).stream().mapToInt((a) -> (a.x - a.h)).min().orElseThrow();
        int xr = List.of(pillars).stream().mapToInt((a) -> (a.x + a.h)).max().orElseThrow();
        int yl = List.of(pillars).stream().mapToInt((a) -> (a.y - a.h)).min().orElseThrow();
        int yr = List.of(pillars).stream().mapToInt((a) -> (a.y + a.h)).max().orElseThrow();
        int hh = Integer.max(xr - xl, yr - yl);
        out.println(String.format("%d %d %d", (xl + xr) / 2, (yl + yr) / 2,
                hh / 2 + hh % 2));
    }
}
