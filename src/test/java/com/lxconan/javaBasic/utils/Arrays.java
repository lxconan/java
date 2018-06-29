package com.lxconan.javaBasic.utils;

public final class Arrays {
    public static int[] toArray(Iterable<Integer> sequence) {
        int capacity = 10;
        int count = 0;
        int[] array = new int[capacity];

        for (Integer integer : sequence) {
            if (count == capacity) {
                int newCapacity = capacity * 2;
                int[] newArray = new int[newCapacity];
                System.arraycopy(array, 0, newArray, 0, array.length);
                capacity = newCapacity;
                array = newArray;
            }

            array[count++] = integer;
        }

        int[] returned = new int[count];
        System.arraycopy(array, 0, returned, 0, count);
        return returned;
    }
}
