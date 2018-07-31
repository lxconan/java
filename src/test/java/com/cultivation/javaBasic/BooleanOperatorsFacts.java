package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanOperatorsFacts {

    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions"})
    @Test
    void should_perform_logical_boolean_operations() {
        boolean[] actualResults = {
            true && true,
            true && false,
            false && false,
            true || true,
            true || false,
            false || false,
            true & true,
            true & false,
            false & false,
            true | true,
            true | false,
            false | false,
            3 == 7,
            4 != 5
        };

        // TODO: please modify the following code to pass the test
        // <--start
        boolean[] expectedResult = {
            true, false, false, true, true, false, true, false, false, true, true, false, false, true
        };
        // --end-->

        assertArrayEquals(expectedResult, actualResults);
    }

    @Test
    void should_do_bitwise_and_boolean_operation() {
        final int value = 0x1234_abcd;
        final int mask = 0x000f_ff00;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x0004_ab00;
        // --end-->

        assertEquals(expected, value & mask);
    }

    @Test
    void should_do_bitwise_or_boolean_operation() {
        final int value = 0x1234_0000;
        final int mask = 0x0000_abcd;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0x1234_abcd;
        // --end-->

        assertEquals(expected, value | mask);
    }

    @Test
    void should_do_bitwise_not_operation() {
        final int value = 0x0000_ffff;

        // TODO: please write down the result directly to pass the test.
        // <--start
        final int expected = 0xffff_0000;
        // --end-->

        assertEquals(expected, ~value);
    }
}
