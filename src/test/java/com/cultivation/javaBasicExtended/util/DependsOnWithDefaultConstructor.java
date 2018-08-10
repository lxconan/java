package com.cultivation.javaBasicExtended.util;

public class DependsOnWithDefaultConstructor {
    private final WithDefaultConstructor dependent;

    public DependsOnWithDefaultConstructor(WithDefaultConstructor dependent) {
        this.dependent = dependent;
    }

    @Override
    public String toString() {
        return String.format("DependsOnWithDefaultConstructor depends on %s", dependent.toString());
    }

    public WithDefaultConstructor getDependent() {
        return dependent;
    }
}
