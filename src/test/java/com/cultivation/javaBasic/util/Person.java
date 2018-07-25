package com.cultivation.javaBasic.util;

public interface Person {
    default String getName() {
        return "Person";
    }
}
