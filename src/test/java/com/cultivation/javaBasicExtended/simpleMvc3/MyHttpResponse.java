package com.cultivation.javaBasicExtended.simpleMvc3;

import java.util.HashMap;
import java.util.Map;

interface MyHttpResponse {
    Map<String,String> getHeaders();
    int getStatusCode();
    long getBodySize();
    byte[] getBodyBuffer();
    void setBodyBuffer(byte[] bytes);
    void setStatusCode(int statusCode);
}

class MyHttpResponseImpl implements MyHttpResponse {
    private byte[] buffer;
    private int statusCode;
    private final HashMap<String, String> headers = new HashMap<>();

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public long getBodySize() {
        return buffer.length;
    }

    @Override
    public byte[] getBodyBuffer() {
        return buffer;
    }

    @Override
    public void setBodyBuffer(byte[] bytes) {
        if (bytes == null) {throw new IllegalArgumentException();}
        buffer = bytes;
    }

    @Override
    public void setStatusCode(int statusCode) {
        if (statusCode < 0 || statusCode > 999) {
            throw new IllegalArgumentException();
        }

        this.statusCode = statusCode;
    }
}