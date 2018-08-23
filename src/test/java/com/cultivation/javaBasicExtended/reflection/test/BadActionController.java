package com.cultivation.javaBasicExtended.reflection.test;

import com.cultivation.javaBasicExtended.reflection.framework.Response;

public class BadActionController {
    public Object badAction() { return null; }
    public Response badActionWithParameter(String arg) { return null; }
}
