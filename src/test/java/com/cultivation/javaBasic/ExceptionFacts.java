package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.StackFrameHelper;
import com.cultivation.javaBasic.util.ClosableStateReference;
import com.cultivation.javaBasic.util.MyClosableType;
import com.cultivation.javaBasic.showYourIntelligence.StringFormatException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExceptionFacts {
    @Test
    void should_customize_exception() {
        try {
            throw new StringFormatException("the message");
        } catch (StringFormatException error) {
            assertEquals("the message", error.getMessage());
        }
    }

    @Test
    void should_customize_exception_continued() {
        Exception innerError = new Exception("inner error");

        try {
            throw new StringFormatException("the message", innerError);
        } catch (StringFormatException error) {
            assertEquals("the message", error.getMessage());
            assertEquals(innerError, error.getCause());
        }
    }

    @Test
    void should_be_careful_of_the_order_of_finally_block() {
        int confusedResult = confuse(2);

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedResult = 0;
        // --end-->

        assertEquals(expectedResult, confusedResult);
    }

    @Test
    void should_use_the_try_pattern() {
        ClosableStateReference closableStateReference = new ClosableStateReference();
        try (MyClosableType closable = new MyClosableType(closableStateReference))
        {
            assertFalse(closable.isClosed());
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), closableStateReference.isClosed());
    }

    @Test
    void should_get_method_name_in_stack_frame() {
        String methodName = StackFrameHelper.getCurrentMethodName();

        assertEquals(
            "com.cultivation.javaBasic.ExceptionFacts.should_get_method_name_in_stack_frame",
            methodName);
    }

    @SuppressWarnings({"ReturnInsideFinallyBlock", "SameParameterValue"})
    private int confuse(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }
}

/*
 * - Please draw the hibachi of `Throwable` and explain the main purpose for each type.
 * - When do you have to declare a exception in the method signature.
 * - When you declare a class A in package PA, and A contains a method
 *   `callMeToDeath() throw FileNotFoundException`. Package PB imports PA and uses
 *   `PA.A.callMeToDeath()`. Both PA and PB are built and deployed. Now PA is updated and
 *   the method `callMeToDeath()` throws another exception. Can we just build and replace
 *   PA?
 * - Can you declare a method throws unchecked exceptions?
 * - If a super class method throws no checked exception, could a derived class override its
 *   method and throw checked exceptions?
 * - Which constructor do you want to implement if you provide your own exception.
 */
