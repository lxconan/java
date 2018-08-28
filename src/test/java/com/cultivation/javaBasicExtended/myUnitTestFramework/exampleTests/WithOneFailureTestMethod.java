package com.cultivation.javaBasicExtended.myUnitTestFramework.exampleTests;

import com.cultivation.javaBasicExtended.myUnitTestFramework.MyAssert;
import com.cultivation.javaBasicExtended.myUnitTestFramework.MyTest;

public class WithOneFailureTestMethod {
    @SuppressWarnings("unused")
    @MyTest
    public void should_fail() {
        MyAssert.assertEquals("Hello", "World");
    }
}
