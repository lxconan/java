package com.lxconan.javaBasic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringFacts {

    @SuppressWarnings("StringEquality")
    @Test
    public void should_be_immutable() {
        String originalString = "The original string";
        String modifiedString = originalString.replace("original", "new");

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        final boolean areSame = true;

        assertEquals("The new string", modifiedString);
        assertEquals(areSame, originalString == modifiedString);
    }

    @SuppressWarnings("StringEquality")
    @Test
    public void all_modification_method_will_create_new_string() {
        String originalString = "The string with tailing space.     ";
        String modifiedString = originalString.trim();

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        final boolean areSame = true;

        assertEquals("The string with tailing space.", modifiedString);
        assertEquals(areSame, originalString == modifiedString);
    }

    @SuppressWarnings("StringEquality")
    @Test
    public void will_create_new_string_when_concat() {
        String originalString = "Part one. ";
        String copyOfOriginalString = originalString;
        originalString += "Part two.";

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        final boolean areSame = true;

        assertEquals("Part one. Part two.", originalString);
        assertEquals(areSame, originalString == copyOfOriginalString);
    }

    @SuppressWarnings("unused")
    @Test
    public void should_taken_string_apart() {
        final String originalString = "Java is great.";

        // TODO: Take part of the original string according to expectation.
        final String partOfString = null;

        final String expectedString = "is great";

        assertEquals(expectedString, partOfString);
    }

    @SuppressWarnings("unused")
    @Test
    public void should_taken_string_apart_continued() {
        final String originalString = "Java is great.";

        // TODO: Take part of the original string according to expectation.
        final String partOfString = null;

        final String expectedString = "is";

        assertEquals(expectedString, partOfString);
    }

    /*
     * Questions on take string apart.
     *
     * - What if the input arguments is out of range of the string?
     * - What will happen if the the starting index is greater than the ending index?
     * - What will happen if the input string is of null reference?
     */
}
