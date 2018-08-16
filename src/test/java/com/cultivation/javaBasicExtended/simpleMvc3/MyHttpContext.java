package com.cultivation.javaBasicExtended.simpleMvc3;

class MyHttpContext {
    private final MyHttpRequest request;
    private final MyHttpResponse response;

    MyHttpContext(MyHttpRequest request, MyHttpResponse response) {
        this.request = request;
        this.response = response;
    }

    public MyHttpRequest getRequest() {
        return request;
    }

    public MyHttpResponse getResponse() {
        return response;
    }
}
