package com.github.claudiohigashi.sorting.radix;

import java.util.LinkedList;
import java.util.List;

/**
 * This implementation uses Radix sort algorithm to sort an array of positive integers
 */
public class RadixSort {

    private static final String[] ZEROS_TABLE = new String[]{
            "",
            "0",
            "00",
            "000",
            "0000",
            "00000",
            "000000",
            "0000000",
            "00000000",
            "000000000",
            "0000000000"
    };

    public static void sort(int[] a) {
        if (a == null || a.length == 0) {
            return; // empty array (there is nothing to sort)
        }
        String[] numbers = createStringArrayFromIntArray(a);
        int digits = numbers[0].length();
        for (int d = digits - 1; d >= 0; d--) {
            List<String>[] buckets = createEmptyBuckets();
            for (int i = 0; i < numbers.length; i++) {
                String number = numbers[i];
                int digit = getCurrentDigit(number.charAt(d));
                buckets[digit].add(number);
            }
            copyBucketsBackToNumbersArray(buckets, numbers);
        }
        copyNumbersBackToIntArray(numbers, a);
    }

    private static void copyNumbersBackToIntArray(String[] numbers, int[] a) {
        for (int i = 0; i < numbers.length; i++) {
            a[i] = Integer.parseInt(numbers[i]);
        }
    }

    private static void copyBucketsBackToNumbersArray(List<String>[] buckets, String[] numbers) {
        int j = 0;
        for (int i = 0; i < buckets.length; i++) {
            List<String> bucket = buckets[i];
            for (String number : bucket) {
                numbers[j++] = number;
            }
        }
    }

    private static int getCurrentDigit(char c) {
        return c - '0';
    }

    private static List<String>[] createEmptyBuckets() {
        List<String>[] buckets = new List[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        return buckets;
    }

    private static String[] createStringArrayFromIntArray(int[] a) {
        String[] strings = new String[a.length];

        // Convert int to String and verify the maximum length (number of digits in a number)
        int maxLength = 0;
        for (int i = 0; i < strings.length; i++) {
            String strNumber = Integer.toString(a[i]);
            strings[i] = strNumber;
            if (strNumber.length() > maxLength) {
                maxLength = strNumber.length();
            }
        }

        // Complete numbers with leading zeros
        for (int i = 0; i < strings.length; i++) {
            strings[i] = completeLeadingZeros(strings[i], maxLength);
        }

        return strings;
    }

    private static String completeLeadingZeros(String string, int maxLength) {
        int leadingZeros = maxLength - string.length();
        if (leadingZeros <= 0) {
            return string;
        }
        return ZEROS_TABLE[leadingZeros] + string;
    }

}
