package com.cultivation.javaBasic.util;

public class SimpleObjectWithInternalState {
    private String name;

    public SimpleObjectWithInternalState(String name) {
        if (name == null) throw new IllegalArgumentException();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
