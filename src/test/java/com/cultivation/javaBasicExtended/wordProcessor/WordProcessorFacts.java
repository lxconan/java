package com.cultivation.javaBasicExtended.wordProcessor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WordProcessorFacts {
    @Test
    void should_throw_on_null() {
        final int validWidth = 10;

        assertThrows(IllegalArgumentException.class, () ->
            new TextProcessor(validWidth).process(null));
    }

    @Test
    void should_throw_on_invalid_width() {
        int[] invalidWidths = {-1, 9, 81, Integer.MAX_VALUE};

        for (int invalidWidth : invalidWidths) {
            assertThrows(IllegalArgumentException.class, () ->
                new TextProcessor(invalidWidth).process(null));
        }
    }

    @Test
    void should_process_single_word() {
        String processed = new TextProcessor(10).process("word");
        assertEquals("word(1);", processed);
    }

    @Test
    void should_process_multiple_word_in_single_line() {
        String processed = new TextProcessor(30).process(
            "The main theme of education in engineering school is learning to teach yourself");
        assertEquals(
            "The(1); (1);main(1); (1);theme(1); (1);of(1); (1);education(1); (1);in(1); (2);engineering(2); (2);school(2); (2);is(2); (2);learning(2,3); (3);to(3); (3);teach(3); (3);yourself(3);",
            processed
        );
    }

    @Test
    void should_process_multiple_word_in_single_line_continued() {
        String processed = new TextProcessor(10).process("So   many whitespaces");
        assertEquals(
            "So(1);   (1);many(1); (1);whitespaces(2,3);",
            processed
        );
    }
}
