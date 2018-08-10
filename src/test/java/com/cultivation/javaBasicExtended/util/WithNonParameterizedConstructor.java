package com.cultivation.javaBasicExtended.util;

public class WithNonParameterizedConstructor {
    private final String text;

    public WithNonParameterizedConstructor() {
        text = "WithNonParameterizedConstructor";
    }

    @Override
    public String toString() {
        return text;
    }
}
