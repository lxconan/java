package com.cultivation.javaBasicExtended.myUnitTestFramework.exampleTests;

import com.cultivation.javaBasicExtended.myUnitTestFramework.MyAssert;
import com.cultivation.javaBasicExtended.myUnitTestFramework.MyTest;

public class WithMultipleFailures {
    @SuppressWarnings("unused")
    @MyTest
    public void should_fail_first() {
        MyAssert.assertEquals("Hello", "World");
    }

    @SuppressWarnings("unused")
    @MyTest
    public void should_fail_second() {
        throw new UnsupportedOperationException("I am the king of the world");
    }
}
