package com.cultivation.javaBasicExtended.reflection.test;

import com.cultivation.javaBasicExtended.reflection.framework.Response;

public class UnhandledExceptionController {
    public Response fail() {
        throw new RuntimeException("Oh god!");
    }
}
