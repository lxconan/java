package com.lxconan.javaBasic;

import com.lxconan.javaBasic.showYourIntelligence.MyIterativeSequence;
import com.lxconan.javaBasic.showYourIntelligence.MyStack;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CollectionFacts {
    @Test
    public void should_resize_array() {
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

    @SuppressWarnings("unused")
    private static Collection<String> distinct(Iterable<String> items) {
        // TODO: Create distinct collection from items.
        throw new NotImplementedException();
    }

    @Test
    public void should_distinct_items() {
        final List<String> names = Arrays.asList("Kayla", "Sofia", "Rob", "Abby", "Kayla");
        Collection<String> distinct = distinct(names);

        assertEquals(4, distinct.size());
        assertTrue(distinct.contains("Kayla"));
        assertTrue(distinct.contains("Sofia"));
        assertTrue(distinct.contains("Rob"));
        assertTrue(distinct.contains("Abby"));
    }

    @Test
    public void should_not_necessarily_put_iterative_sequence_into_memory() {
        Iterable<Integer> sequence = new MyIterativeSequence(5, 20);
        int[] array = com.lxconan.javaBasic.utils.Arrays.toArray(sequence);

        assertArrayEquals(new int[] {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, array);
    }
}
