package main.stree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class STreeTest<T> {
    @Test
    void checkAdd() {
        int[] origArray = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        STreeHalfIntervals<Integer> stree = new STreeHalfIntervals<>(origArray.length, Integer::sum, (a, b) -> a * b, 0);
        for (int i = 0; i < origArray.length; i++) {
            stree.set(i, origArray[i]);
        }
        for (int i = 0; i < origArray.length; i++) {
            for (int j = i; j < origArray.length; j++) {
                int correct = Arrays.stream(origArray).skip(i).limit(j-i).sum();
                int toCheck = stree.get(i, j);
                assertEquals(correct, toCheck, String.format("%s %s", i, j));
            }
        }
    }

    @Test
    void checkMin() {
        int[] origArray = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        STreeHalfIntervals<Integer> stree = new STreeHalfIntervals<>(origArray.length, Integer::min, (a, b) -> a, Integer.MAX_VALUE);
        for (int i = 0; i < origArray.length; i++) {
            stree.set(i, origArray[i]);
        }
        for (int i = 0; i < origArray.length; i++) {
            for (int j = i + 1; j < origArray.length; j++) {
                int correct = Arrays.stream(origArray).skip(i).limit(j-i).min().orElseThrow();
                int toCheck = stree.get(i, j);
                assertEquals(correct, toCheck, String.format("%s %s", i, j));
            }
        }
    }

    @Test
    void checkSet() {
        int[] origArray = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        STreeHalfIntervals<Integer> stree = new STreeHalfIntervals<>(origArray.length, Integer::min, (a, b) -> a, Integer.MAX_VALUE);
        for (int i = 0; i < origArray.length; i++) {
            stree.set(i, origArray[i]);
        }
        stree.set(0, 100);
        origArray[0] = 100;
        stree.set(6, 200);
        origArray[6] = 200;
        stree.set(2, 300);
        origArray[2] = 300;
        for (int i = 0; i < origArray.length; i++) {
            for (int j = i + 1; j < origArray.length; j++) {
                int correct = Arrays.stream(origArray).skip(i).limit(j-i).min().orElseThrow();
                int toCheck = stree.get(i, j);
                assertEquals(correct, toCheck, String.format("%s %s", i, j));
            }
        }
    }

    @Test
    void checkMultiSet() {
        int[] origArray = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        STreeHalfIntervals<Integer> stree = new STreeHalfIntervals<>(origArray.length, Integer::min, (a, b) -> a, Integer.MAX_VALUE);
        for (int i = 0; i < origArray.length; i++) {
            stree.set(i, origArray[i]);
        }
        stree.set(0, 100);
        origArray[0] = 100;
        stree.set(6, 200);
        origArray[6] = 200;
        stree.set(2, 300);
        origArray[2] = 300;
        for (int i = 0; i < origArray.length; i++) {
            for (int j = i + 1; j < origArray.length; j++) {
                int correct = Arrays.stream(origArray).skip(i).limit(j-i).min().orElseThrow();
                int toCheck = stree.get(i, j);
                assertEquals(correct, toCheck, String.format("%s %s", i, j));
            }
        }
    }
}
