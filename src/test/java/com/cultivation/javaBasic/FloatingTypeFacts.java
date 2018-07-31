package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatingTypeFacts {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNunber = 2.75f;
        final int integer = (int)floatingPointNunber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isNaN(realNumber);
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isInfinite(realNumber);
    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}
