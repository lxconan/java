package com.cultivation.javaBasicExtended.simpleMvc3;

class MyHttpException extends RuntimeException {
    private final int statusCode;

    MyHttpException(int statusCode) { this.statusCode = statusCode; }

    int getStatusCode() { return statusCode; }
}
