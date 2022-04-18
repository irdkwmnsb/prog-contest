package main;

import java.util.Arrays;

public class IntMatrix {
    int[][] m;
    int a, b;

    IntMatrix(int a, int b) {
        this.a = a;
        this.b = b;
        m = new int[a][b];
    }

    IntMatrix(int a, int b, int[][] m) {
        this(a, b);
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                this.m[i][j] = m[a][b];
            }
        }
    }

    IntMatrix multiply(IntMatrix b) {
        if (this.b != b.a) {
            throw new RuntimeException("Matrices cannot be multipled");
        }
        IntMatrix e = new IntMatrix(a, b.b);
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b.b; j++) {
                for (int k = 0; k < this.b; k++) {
                    e.m[i][j] = e.m[i][k] + e.m[k][j];
                }
            }
        }
        return e;
    }
}
