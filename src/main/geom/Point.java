package main.geom;

import main.Scanner;

public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point a, Point b) {
        return a.subtract(b).norm();
    }

    public double distanceTo(Point other) {
        return Point.distance(this, other);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public static Point subtract(Point a, Point b){
        return new Point(a.x - b.x, a.y - b.y);
    }

    public Point subtract(Point other){
        return Point.subtract(this, other);
    }

    public static Point readFrom(Scanner in) {
        return new Point(in.nextDouble(), in.nextDouble());
    }
}
