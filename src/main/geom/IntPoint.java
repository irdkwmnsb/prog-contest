package main.geom;

import main.Scanner;

public class IntPoint {
    int x, y;

    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(IntPoint a, IntPoint b) {
        return a.subtract(b).norm();
    }

    public double distanceTo(IntPoint other) {
        return IntPoint.distance(this, other);
    }

    public static int manhattan(IntPoint a, IntPoint b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public int manhattanTo(IntPoint other) {
        return IntPoint.manhattan(this, other);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public static IntPoint subtract(IntPoint a, IntPoint b) {
        return new IntPoint(a.x - b.x, a.y - b.y);
    }

    public IntPoint subtract(IntPoint other) {
        return IntPoint.subtract(this, other);
    }

    public static IntPoint add(IntPoint a, IntPoint b) {
        return new IntPoint(a.x + b.x, a.y + b.y);
    }

    public IntPoint add(IntPoint other) {
        return IntPoint.add(this, other);
    }

    public static IntPoint readFrom(Scanner in) {
        return new IntPoint(in.nextInt(), in.nextInt());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", x, y);
    }
}
