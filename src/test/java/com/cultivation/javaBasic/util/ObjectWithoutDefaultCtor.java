package com.cultivation.javaBasic.util;

public class ObjectWithoutDefaultCtor implements Cloneable {
    private int value;

    public ObjectWithoutDefaultCtor(int value) {

        this.value = value;
    }

    public ObjectWithoutDefaultCtor clone() throws CloneNotSupportedException {
        return (ObjectWithoutDefaultCtor)super.clone();
    }

    public int getValue() {
        return value;
    }
}
