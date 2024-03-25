package main;

import main.Scanner;
import java.io.PrintWriter;

public class B {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        double e = in.nextDouble();
        int n = 0;

        int[] answers = {0, 0};

        while (true) {
            System.out.println(-1);
            int answer = in.nextInt();
            answers[answer] += 1;
            double mat = answers[1] * 1.0 / (answers[0] + answers[1]);
            n++;
            double disp = Math.sqrt(mat - mat * mat);
            if (Math.sqrt(n) > 1 / e) {
                if (disp < e || Phi((e * Math.sqrt(n)) / disp) > 0.99) {
                    System.out.println(mat);
                    return;
                }
            }
        }
    }

    public static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        double ans = 1 - t * Math.exp(-z * z - 1.26551223 +
                t * (1.00002368 +
                        t * (0.37409196 +
                                t * (0.09678418 +
                                        t * (-0.18628806 +
                                                t * (0.27886807 +
                                                        t * (-1.13520398 +
                                                                t * (1.48851587 +
                                                                        t * (-0.82215223 +
                                                                                t * (0.17087277))))))))));
        if (z >= 0) {
            return ans;
        } else {
            return -ans;
        }
    }

    public static double Phi(double z) {
        return 0.5 * (1 + erf(z / Math.sqrt(2.)));
    }
}
