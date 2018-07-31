package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaFacts {
    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        StringFunc lambda = () -> "Hello";

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
        StringFunc lambda = this::instanceMethod;
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
        StringFunc lambda = LambdaFacts::staticMethod;
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = ArrayList::new;
        // --end-->

        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {
        int captured = 5;

        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() {
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4";
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }

    @Test
    void should_extend_variable_scope() {
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    private static String staticMethod() {
        return "staticMethod";
    }

    private String instanceMethod() {
        return "instanceMethod";
    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
