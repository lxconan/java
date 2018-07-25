package com.cultivation.javaBasic.util;

public interface InterfaceWithDefaultMethod {
    default String tellMeTheTruthOfTheUniverse() {
        return "The truth of the universe is " + getTheTruthOfTheUniverse();
    }

    default String getTheTruthOfTheUniverse() {
        return "42";
    }
}

