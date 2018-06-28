package com.lxconan.javaBasic;

import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

import static org.junit.Assert.*;

public class NumbersFacts {
    @Test
    public void should_convert_string_to_numbers() {
        final String textOfValidNumber = "123";
        final String textOfInvalidNumber = "aaa";

        Optional<Integer> successResult = convertToInteger(textOfValidNumber);
        Optional<Integer> failureResult = convertToInteger(textOfInvalidNumber);

        assertTrue(successResult.isPresent());
        assertEquals(123, successResult.get().intValue());
        assertFalse(failureResult.isPresent());
    }

    @SuppressWarnings("unused")
    private Optional<Integer> convertToInteger(String text) {
        // TODO: Please implement the method to conver text into an integer.
        // If the conversion succeeded, An integer should be returned, or an empty result will be returned.
        return Optional.empty();
    }

    @Test
    public void should_get_boundaries_of_certain_number_type() {
        final int maximum = 0x7fffffff;

        // TODO: You should not write concrete number here. Please find a property or constant instead.
        // <!--start
        final int maximumSymbol = 0;
        // --end-->

        assertEquals(maximumSymbol, maximum);
    }

    @Test
    public void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNunber = 2.75f;
        final int integer = (int)floatingPointNunber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 0;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void should_be_able_to_convert_primitive_numbers_to_objects() {
        final float floatNumber = 2.75f;
        final double doubleNumber = 2.75;
        final int integer = 2;
        final long longInteger = 2;

        // TODO: convert the above result to objects.
        // <--start
        final Float floatNumberObject = null;
        final Double doubleNumberObject = null;
        final Integer integerObject = null;
        final Long longObject = null;
        // --end-->

        assertNotNull(floatNumberObject);
        assertNotNull(doubleNumberObject);
        assertNotNull(integerObject);
        assertNotNull(longObject);

        assertEquals(floatNumber, floatNumberObject, 0f);
        assertEquals(doubleNumber, doubleNumberObject, 0);
        assertEquals(integer, integerObject.intValue());
        assertEquals(longInteger, longObject.longValue());
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    public void should_take_care_of_number_type_when_doing_calculation() {
        final double result1 = 2 / 3 * 5;
        final double result2 = 2 * 5 / 3;

        // TODO: please modify the following lines to pass the test
        // <!--start
        final double expectedResult1 = 0xbad;
        final double expectedResult2 = 0xbad;
        // --end-->

        assertEquals(expectedResult1, result1, 0);
        assertEquals(expectedResult2, result2, 0);
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    public void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    public void should_round_floating_point_number() {
        final double threshold = 0.54;
        final double smallerThanThreshold = 1.52;
        final double greaterThanThreshold = 1.545;

        assertEquals(1, roundToInteger(smallerThanThreshold, threshold));
        assertEquals(2, roundToInteger(greaterThanThreshold, threshold));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    public void should_be_formatted_as_specified_pattern() {
        final double number = 1024.2345678;

        // TODO: Please format the number as #,##0.### programmatically.
        String formattedResult = null;

        assertEquals("1,024.235", formattedResult);
    }

    @Test
    public void should_multiply_matrix() {
        final int[][] x = {
            { 3, 2, 3 },
            { 5, 9, 8 }
        };
        final int[][] y = {
            {4, 7},
            {9, 3},
            {8, 1}
        };

        int[][] z = multiplyMatrix(x, y);

        assertEquals(12 + 18 + 24, z[0][0]);
        assertEquals(21 + 6 + 3, z[0][1]);
        assertEquals(20 + 81 + 64, z[1][0]);
        assertEquals(35 + 27 + 8, z[1][1]);
    }

    @SuppressWarnings("unused")
    private int[][] multiplyMatrix(int[][] x, int[][] y) {
        // TODO: Please implement the method to pass the test
        throw new NotImplementedException();
    }

    @SuppressWarnings({"unused", "SameParameterValue"})
    private int roundToInteger(double value, double threshold) {
        // TODO: Please implement the method to pass the test
        return 0;
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return false;
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        throw new NotImplementedException();
    }
}
