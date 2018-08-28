package com.cultivation.javaBasic.util;

import java.util.List;

public class ClosableWithoutException implements AutoCloseable {
    private List<String> logger;

    public ClosableWithoutException(List<String> logger) {
        this.logger = logger;
    }

    @Override
    public void close() {
        logger.add("ClosableWithoutException.close");
    }
}

