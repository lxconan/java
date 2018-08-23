package com.cultivation.javaBasicExtended.reflection.framework;

public class Response {
    private final Object value;
    private final int status;

    public Response(Object value, int status) {
        this.value = value;
        this.status = status;
    }

    public Response(int status) {
        this(null, status);
    }

    public Object getValue() {
        return value;
    }

    public int getStatus() {
        return status;
    }
}
