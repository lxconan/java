package com.cultivation.javaBasic.util;

@SuppressWarnings("unused")
public class OverloadingFixture {
    public String methodWithOneParameter(String value) {
        return "methodWithOneParameter(String)";
    }

    public String methodWithOneParameter(Object value) {
        return "methodWithOneParameter(Object)";
    }

    public String methodWithTwoParameters(String string, Integer integer) {
        return "methodWithTwoParameters(String, Integer)";
    }

    public String methodWithTwoParameters(String string, Number number) {
        return "methodWithTwoParameters(String, Number)";
    }
}
