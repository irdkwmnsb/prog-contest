package main;

import main.geom.IntPoint;
import main.graphs.Edge;
import main.graphs.Graph;
import main.graphs.MatrixGraph;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Stream;

public class Task1B {
    PrintWriter out;

    class Order {
        int h;
        int m;
        IntPoint from;
        IntPoint to;
        int p;

        public Order(Scanner in) {
            h = in.nextInt();
            char s = in.nextChar();
            m = in.nextInt();
            from = IntPoint.readFrom(in);
            to = IntPoint.readFrom(in);
            p = -1;
        }

        int minutes() {
            return h * 60 + m;
        }

        boolean canThenPickup(Order another) {
            int takesToFinish = this.from.manhattanTo(this.to);
            int takesToGetThere = another.from.manhattanTo(this.to);
            return this.minutes() + takesToFinish + takesToGetThere < another.minutes();
        }
    }

    boolean[] used;
    Graph<Edge> g;
    Order[] orders;

    boolean extend(int v, IntList path) {
        if (!used[v]) {
            used[v] = true;
            for (Edge e: g.getChildren(v)) {
                int con = orders[e.getTo()].p;
                path.push(e.getTo());
                if (con == -1) {
                    return true;
                }
                if (extend(con, path)) {
                    return true;
                }
                path.pop();
            }
        }
        return false;
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        int m = in.nextInt();
        g = new MatrixGraph<>(m);
        orders = Stream.generate(() -> new Order(in)).limit(m).toArray(Order[]::new);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (orders[i].canThenPickup(orders[j])) {
                    g.addEdge(new Edge(i, j));
                }
            }
        }
        used = new boolean[m];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            Arrays.fill(used, false);
            IntList path = new IntList();
            if (!extend(i, path)) {
                ans++;
            }
            int cur = i;
            for (int j = 0; j < path.length(); j++) {
                int tmp = orders[path.get(j)].p;
                orders[path.get(j)].p = cur;
                cur = tmp;
            }
        }
        out.println(ans);
    }
}
