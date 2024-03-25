package main;

import java.util.Scanner;

public class MinimumReproduction {
    public static void main(String[] args) {
        int[] a = {0, 0};
        Scanner scanner = new Scanner(System.in);
        int d = scanner.nextInt();
        a[d]++;
        System.out.println(a[0] + a[1]);
    }
}
