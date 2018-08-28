package com.cultivation.javaBasicExtended.myUnitTestFramework.exampleTests;

import com.cultivation.javaBasicExtended.myUnitTestFramework.MyAssert;
import com.cultivation.javaBasicExtended.myUnitTestFramework.MyTest;

public class WithOneTestMethod {
    @SuppressWarnings("unused")
    @MyTest
    public void should_success() {
        MyAssert.assertEquals("Hello", "Hello");
    }
}

