package com.cultivation.javaBasicExtended.myIoC.util;

import com.cultivation.javaBasicExtended.myIoC.MyIoCInjection;

public class DependsOnWithDefaultConstructor {
    @MyIoCInjection
    private WithDefaultConstructor dependent;

    @Override
    public String toString() {
        return String.format("DependsOnWithDefaultConstructor depends on %s", dependent.toString());
    }

    public WithDefaultConstructor getDependent() {
        return dependent;
    }
}

