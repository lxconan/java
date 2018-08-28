package com.cultivation.javaBasicExtended.myUnitTestFramework;

import java.util.Objects;

public class MyAssert {
    public static void assertEquals(Object left, Object right) {
        if (!Objects.equals(left, right)) {
            throw new MyUnitTestAssertException(String.format("%s does not equal with %s.", left, right));
        }
    }
}
