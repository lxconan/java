package com.cultivation.javaBasicExtended.printMatchedPattern;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Difficulty(DifficultyLevel.EASY)
class MyGrepFacts {
    @Test
    void should_throw_if_reader_is_null_or_pattern_is_null() {
        assertThrows(IllegalArgumentException.class, () -> MyGrep.grep(null, "[A-Z]"));
        assertThrows(IllegalArgumentException.class, () -> MyGrep.grep(new StringReader("Hello"), null));
        assertThrows(IllegalArgumentException.class, () -> MyGrep.grep(null, null));
        assertThrows(IllegalArgumentException.class, () -> MyGrep.grep(new StringReader("Hello"), ""));
    }

    @Test
    void should_print_all_matches() throws IOException {
        String content =
            "A \"class\" is defined by its package, its name, and the class loader it originally loaded.\n" +
            "The program starts and the real \"main\"-class is loaded by this proxy classloader.\n" +
            "Every class that then is normally loaded (i.e. not through another classloader implementation " +
            "which could break the hierarchy) will be delegated to this class loader.\n";
        StringReader reader = new StringReader(content);
        String[] result = MyGrep.grep(reader, "\"[A-Za-z]+\"");

        assertArrayEquals(new String[] {"\"class\"", "\"main\""}, result);
    }
}

