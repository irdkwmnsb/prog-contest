package main;

import main.geom.IntPoint;
import main.graphs.AdjacencyListGraph;
import main.graphs.FlowEdge;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task2C {
    PrintWriter out;
    Field field;
    static int inf = 1000000007;

    class Field {
        int n;
        int m;
        char[][] field;

        Field(Scanner in) {
            n = in.nextInt();
            m = in.nextInt();
            field = Stream.generate(in::nextWord).limit(n).map(String::toCharArray).toArray(char[][]::new);
        }

        boolean walkable(IntPoint p) {
            return getPoint(p) != '#';
        }

        IntPoint getPoint(int v) {
            v /= 2;
            return new IntPoint(v / m, v % m);
        }

        int getV(IntPoint p, boolean odd) {
            return (p.getX() * m + p.getY()) * 2 + (odd ? 1 : 0);
        }

        boolean exists(IntPoint point) {
            return 0 <= point.getX() && point.getX() < n &&
                    0 <= point.getY() && point.getY() < m;
        }

        int getMaxV() {
            return n * m * 2;
        }

        char getPoint(IntPoint p) {
            return field[p.getX()][p.getY()];
        }

        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {-1, 0, 1, 0};

        IntPoint[] neighbours(IntPoint p) {
            List<IntPoint> points = new ArrayList<>(4);
            for (int i = 0; i < 4; i++) {
                IntPoint d = new IntPoint(dx[i], dy[i]);
                IntPoint r = d.add(p);
                if (exists(r)) {
                    points.add(r);
                }
            }
            return points.toArray(IntPoint[]::new);
        }

        void setField(IntPoint p, char c) {
            field[p.getX()][p.getY()] = c;
        }

        void print(PrintWriter out) {
            for (int i = 0; i < n; i++) {
                out.println(new String(field[i]));
            }
        }
    }

    void addEdge(int i, int j, int x) {
        FlowEdge edge = new FlowEdge(i, j, x, -1);
        g.addEdge(edge);
        FlowEdge rev = new FlowEdge(j, i, 0, -1);
        rev.tangle(edge);
        g.addEdge(rev);
    }

    void add(IntPoint from, IntPoint to) {
        if (field.walkable(to)) {
            addEdge(field.getV(from, true), field.getV(to, false), inf);
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        field = new Field(in);
        g = new AdjacencyListGraph<>(field.getMaxV());
        u = new int[field.getMaxV()];
        for (int i = 0; i < field.n; i++) {
            for (int j = 0; j < field.m; j++) {
                IntPoint p = new IntPoint(i, j);
                char value = field.getPoint(p);
                if (value == 'A') {
                    S = field.getV(p, true);
                } else if (value == 'B') {
                    T = field.getV(p, false);
                }
                if (field.walkable(p)) {
                    for (IntPoint neighbour : field.neighbours(p)) {
                        add(p, neighbour);
                    }
                    addEdge(field.getV(p, false), field.getV(p, true), field.getPoint(p) == '.' ? 1 : inf);
                }
            }
        }
        int f = 0;
        while (expand(S, inf)) {
            cc++;
            f += df;
            if (df >= inf / 2) {
                out.println(-1);
                return;
            }
        }
        out.println(f);
        for (int v = 0; v < field.getMaxV(); v++) {
            if (u[v] == cc) {
                for (FlowEdge e : g.getChildren(v)) {
                    if (e.f != 0 && u[e.getTo()] != cc) {
                        field.setField(field.getPoint(v), '+');
                    }
                }
            }
        }
        field.print(out);
    }

    int S;
    int T;
    int df;
    AdjacencyListGraph<FlowEdge> g;
    int[] u;
    int cc = 1;

    boolean expand(int v, int curFlow) {
//        out.println(v + " " + v % 2 + " " + field.getPoint(v) + " " + curFlow);
        if (v == T) {
            df = curFlow;
            return df != 0;
        }
        u[v] = cc;
        for (FlowEdge e : g.getChildren(v)) {
//            out.println(e + " " + u[e.getTo()]);
            if (e.getC() - e.f > 0 && u[e.getTo()] != cc && expand(e.getTo(), Math.min(curFlow, e.getC() - e.f))) {
                e.f += df;
                e.getRev().f -= df;
                return true;
            }
        }
        return false;
    }
}
