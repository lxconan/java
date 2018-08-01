package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamingFacts {
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> wordStream = words.stream())
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(Collectors.toList())
            );
        }
    }

    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> wordStream = Stream.of(words))
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }

    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> emptyWordStream = Stream.empty())
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<String> infiniteEchos = Stream.generate(() -> "Echo"))
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        try (Stream<Integer> infiniteSequence = Stream.iterate(0, item -> ++item))
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings("TryFinallyCanBeTryWithResources")
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        try (Stream<String> filtered = wordStream.filter(w -> w.length() > 4))
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        try (Stream<String> filtered = wordStream.map(String::toUpperCase))
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }
}
