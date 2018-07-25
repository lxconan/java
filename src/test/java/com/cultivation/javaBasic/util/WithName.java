package com.cultivation.javaBasic.util;

public interface WithName {
    default String getName() {
        return "WithName";
    }
}

