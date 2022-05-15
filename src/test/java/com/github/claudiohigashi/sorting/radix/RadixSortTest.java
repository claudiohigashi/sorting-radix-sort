package com.github.claudiohigashi.sorting.radix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RadixSortTest {
    @Test
    void sort1() {
        int[] array = new int[]{3, 7, 8, 5, 2, 1, 9, 6, 4};
        RadixSort.sort(array);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    void sort2() {
        int[] array = new int[]{31, 711, 32, 5, 29, 199, 91, 52, 14};
        RadixSort.sort(array);
        Assertions.assertArrayEquals(new int[]{5, 14, 29, 31, 32, 52, 91, 199, 711}, array);
    }

    @Test
    void sort3() {
        int[] array = new int[]{31, 711, 32, 5, Integer.MAX_VALUE, 29, 199, 91, 0, 14};
        RadixSort.sort(array);
        Assertions.assertArrayEquals(new int[]{0, 5, 14, 29, 31, 32, 91, 199, 711, Integer.MAX_VALUE}, array);
    }
}