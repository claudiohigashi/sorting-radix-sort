package com.github.claudiohigashi.sorting.radix;

import java.util.LinkedList;
import java.util.List;

/**
 * This implementation uses Radix sort algorithm to sort an array of positive integers
 */
public class RadixSort {
    private static final int[] POWER_OF_10 = new int[]{
            1,
            10,
            100,
            1000,
            10000,
            100000,
            1000000,
            10000000,
            100000000,
            1000000000
    };

    public static void sort(int[] a) {
        if (a == null || a.length == 0) {
            return; // empty array (there is nothing to sort)
        }
        for (int d = 0; d < 10; d++) { // maximum integer is 2,147,483,647 (max 10 digits)
            List<Integer>[] buckets = createEmptyBuckets();
            boolean allDigitsAreZero = true;
            for (int i = 0; i < a.length; i++) {
                int number = a[i];
                int digit = getCurrentDigit(number, d);
                if (digit != 0) {
                    allDigitsAreZero = false;
                }
                buckets[digit].add(number);
            }
            if (allDigitsAreZero) {
                break;
            }
            copyBucketsBackToNumbersArray(buckets, a);
        }
    }

    private static void copyBucketsBackToNumbersArray(List<Integer>[] buckets, int[] numbers) {
        int j = 0;
        for (int i = 0; i < buckets.length; i++) {
            List<Integer> bucket = buckets[i];
            for (Integer number : bucket) {
                numbers[j++] = number;
            }
        }
    }

    private static int getCurrentDigit(int number, int power) {
        return (power < 9 ? number % POWER_OF_10[power + 1] : number) / POWER_OF_10[power];
    }

    private static List<Integer>[] createEmptyBuckets() {
        List<Integer>[] buckets = new List[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        return buckets;
    }

}
