package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.MyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayFacts {
    @Test
    void should_resize_array() {
        final int itemsCount = 25;
        final int initialCapacity = 10;

        MyStack myStack = new MyStack(initialCapacity);
        for (int i = 0; i < itemsCount; ++i) {
            myStack.push(i);
        }

        int[] array = myStack.popToArray();

        assertArrayEquals(
            new int[] {24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
            array
        );
    }
}
