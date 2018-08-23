package com.cultivation.javaBasicExtended.reflection.test;

import com.cultivation.javaBasicExtended.reflection.framework.Response;

public class StandardController {
    public Response doSomething() {
        return new Response("Hello", 200);
    }

    Response doSomethingPackagePrivate() {
        return new Response("You cannot see me", 200);
    }
}
