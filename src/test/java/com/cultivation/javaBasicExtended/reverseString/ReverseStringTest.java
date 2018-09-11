package com.cultivation.javaBasicExtended.reverseString;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Difficulty(DifficultyLevel.EASY)
class ReverseStringTest {
    @Test
    void should_be_able_to_reverse_string_split_by_spaces() {
        String[] reversed = StringReverser.reverse("A quick brown fox jumps over a lazy dog");
        assertArrayEquals(
            new String[] {
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
            }, reversed);
    }

    @Test
    void should_throw_if_input_is_null() {
        assertThrows(IllegalArgumentException.class, () -> StringReverser.reverse(null));
    }

    @Test
    void should_get_empty_array_if_input_string_is_empty() {
        String[] reversed = StringReverser.reverse("");

        assertEquals(0, reversed.length);
    }

    @Test
    void should_get_empty_array_if_input_string_is_whitespaced() {
        String[] reversed = StringReverser.reverse( "   ");

        assertEquals(0, reversed.length);
    }
}
