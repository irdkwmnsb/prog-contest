package main;

import main.Scanner;

import java.io.PrintWriter;
import java.util.Arrays;

public class C {
//    static {
//        int[] a = new int[]{2, 5, 4, 7, 6, 8, 1, 3, 0, 9};
//        for(int i = 0; i<a.length; i++)
//            System.err.println(Utils.findOrderStatistic(Arrays.copyOf(a, a.length), i));
//        a = new int[]{2,2,2,2,2,2,2};
//        for(int i = 0; i<a.length; i++)
//            System.err.println(Utils.findOrderStatistic(Arrays.copyOf(a, a.length), i));
//    }
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
        int[] arr = new int[n];
        arr[0] = in.nextInt();
        arr[1] = in.nextInt();
        for (int i = 2; i < n; i++) {
            arr[i] = a * arr[i - 2] + b * arr[i - 1] + c;
        }
        out.println(Utils.findOrderStatistic(arr, k-1));
    }
}
