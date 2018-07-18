package com.cultivation.javaBasic.util;

@SuppressWarnings("ALL")
public class CallingAnotherCtor {
    private final String name;

    public CallingAnotherCtor() {
        this("Untitled");
    }

    public CallingAnotherCtor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
