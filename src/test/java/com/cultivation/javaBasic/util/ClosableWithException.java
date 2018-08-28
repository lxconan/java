package com.cultivation.javaBasic.util;

import java.util.List;

public class ClosableWithException implements AutoCloseable {
    private List<String> logger;

    public ClosableWithException(List<String> logger) {
        this.logger = logger;
    }

    @Override
    public void close() {
        logger.add("ClosableWithException.close");
        throw new IllegalArgumentException("Oh god!");
    }

}
