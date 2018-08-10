package com.cultivation.javaBasicExtended.myIoC.util;

public class WithParameterizedConstructor {
    static {
        // I am here to make you confused.
    }

    private WithDefaultConstructor dependent;
    public WithParameterizedConstructor(WithDefaultConstructor dependent) {
        this.dependent = dependent;
    }
}
