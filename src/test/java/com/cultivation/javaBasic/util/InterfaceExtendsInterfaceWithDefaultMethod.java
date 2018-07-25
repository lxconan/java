package com.cultivation.javaBasic.util;

public interface InterfaceExtendsInterfaceWithDefaultMethod extends InterfaceWithDefaultMethod {
    @Override
    default String getTheTruthOfTheUniverse() {
        return "Food";
    }
}
