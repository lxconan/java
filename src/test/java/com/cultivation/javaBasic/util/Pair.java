package com.cultivation.javaBasic.util;

public class Pair<T> {
    private T first;
    private T second;

    public Pair(){}

    public Pair(T first, T second) {
        setFirst(first);
        setSecond(second);
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
