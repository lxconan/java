package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.EscapedChars;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharTypeFacts {
    @Test
    void should_describe_escaped_chars() {
        final char backspace = '\b';
        final char tab = '\t';
        final char lineFeed = '\n';
        final char carriageReturn = '\r';
        final char doubleQuote = '\"';
        final char singleQuote = '\'';
        final char backslash = '\\';

        assertEquals(EscapedChars.BACKSPACE.getValue(), backspace);
        assertEquals(EscapedChars.TAB.getValue(), tab);
        assertEquals(EscapedChars.LINE_FEED.getValue(), lineFeed);
        assertEquals(EscapedChars.CARRIAGE_RETURN.getValue(), carriageReturn);
        assertEquals(EscapedChars.DOUBLE_QUOTE.getValue(), doubleQuote);
        assertEquals(EscapedChars.SINGLE_QUOTE.getValue(), singleQuote);
        assertEquals(EscapedChars.BACKSLASH.getValue(), backslash);
    }

    /*
     * - Could a char represent one unicode character? Or, in other words, could a char represent a code point?
     * - How many bits are needed to represents one code point in UTF-16? What about UTF-8 and UTF-32?
     * - In Java, which encoding is used by char type? The UTF-16 encoding or UTF-8 encoding.
     * - Why there are many methods in Character class accepting an int parameter rather than char?
     */
}
