package main;


import java.io.PrintWriter;

public class num2part {
    PrintWriter out;
    long[][] d;
    int n;
    long r;
    int sum;
    int l = 0;
    IntList ans = new IntList();

    void generate() {
        if (l == sum) {
            ans.push(sum);
            for (int i = 0; i < ans.length(); i++) {
                if (i > 0)
                    out.print("+");
                out.print(ans.get(i));
            }
            return;
        }
        if (r >= d[sum][l]) {
            r -= d[sum][l];
            l++;
            generate();
            l--;
        } else {
            sum -= l;
            ans.push(l);
            generate();
            sum += l;
        }
    }

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.out = out;
        n = in.nextInt();
        sum = n;
        r = in.nextLong();
        d = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    for (int k = j; k <= i; k++) {
                        d[i][j] += d[i - j][k];
                    }
                } else {
                    d[i][j] = 1;
                }
            }
        }
        generate();
    }
}
