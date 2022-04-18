package main;

import main.Scanner;
import java.io.PrintWriter;

public class Task01A {
    PrintWriter out;
    int n, m;
    boolean[][] been;
    boolean[][] f;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    void dfs(int x, int y) {
        been[x][y] = true;
        for(int i = 0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(Utils.inRange(0, n-1, nx) && Utils.inRange(0, m-1, ny)) {
                if(!been[nx][ny] && f[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt();
        m = in.nextInt();
        been = new boolean[n][];
        f = new boolean[n][];
        for(int i = 0; i<n; i++) {
            String l = in.nextWord();
            been[i] = new boolean[m];
            f[i] = new boolean[m];
            for(int j = 0; j<m; j++) {
                f[i][j] = l.charAt(j) == 'O';
            }
        }
        int ans = 0;
        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(!been[i][j] && f[i][j]) {
                    dfs(i, j);
                    ans++;
                }
            }
        }
        out.println(ans);
    }
}
