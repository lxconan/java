package com.lxconan.javaBasic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    public void should_break_string_into_words() {
        final String sentence = "This is Mike";

        // TODO: Extract words in the sentence.
        // <--Start
        String[] words = null;
        // --End-->

        assertArrayEquals(new String[] {"This", "is", "Mike"}, words);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    public void should_break_string_into_words_customized() {
        final String sentence = "This/is/Mike";

        // TODO: Extract words in the sentence.
        // <--Start
        String[] words = null;
        // --End-->

        assertArrayEquals(new String[] {"This", "is", "Mike"}, words);
    }

    @SuppressWarnings({"unused", "StringBufferReplaceableByString", "MismatchedQueryAndUpdateOfStringBuilder"})
    @Test
    public void should_create_ascii_art() {
        final int width = 5;
        final int height = 3;

        // TODO: Create string using StringBuilder
        // <--Start
        StringBuilder builder = new StringBuilder();
        // --End-->

        final String expected =
            "|---|\n" +
            "|   |\n" +
            "|---|\n";

        assertEquals(expected, builder.toString());
    }

    @SuppressWarnings("unused")
    @Test
    public void should_calculate_checksum_of_a_string() {
        final String text = "A quick brown fox jumps over a lazy dog.";

        int sum = 0;
        // TODO: Write some code to calculate the checksum of the string. The checksum is the sum of each string char.
        // <--Start
        // --End-->

        Assert.assertEquals(3655, sum);
    }

    @Test
    public void should_convert_unicode_escape() {
        final String expected = "なにこれ";

        // TODO: Write actual string using unicode escape. The unicode is as follows:
        // な - U+306a
        // に - U+306b
        // こ - U+3053
        // れ - U+308c
        // <--Start
        final String actual = "";
        // --End-->

        assertEquals(expected, actual);
    }

    @SuppressWarnings("unused")
    @Test
    public void should_reverse_a_string() {
        final String original = "123456";

        // TODO: Modify the following code to create new string from original String
        // <--Start
        final String reversed = "";
        // --End-->

        assertEquals("654321", reversed);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void should_compare_string_with_different_cases() {
        final String uppercased = "HELLO";
        final String lowercased = "hello";

        Optional<Boolean> equalResult = Optional.of(uppercased.equals(lowercased));
        Optional<Boolean> equalIgnoreCaseResult = Optional.of(uppercased.equalsIgnoreCase(lowercased));

        // TODO: Please change the value of the following 2 lines to pass the test.
        Optional<Boolean> actualResultOfEqual = Optional.empty();
        Optional<Boolean> actualResultOfEqualIgnoreCase = Optional.empty();

        assertEquals(equalResult, actualResultOfEqual);
        assertEquals(equalIgnoreCaseResult, actualResultOfEqualIgnoreCase);
    }
}
