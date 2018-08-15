package com.cultivation.javaBasicExtended.simpleMvc2;

import feign.RequestLine;
import feign.Response;

interface SimpleHttpServerClient {
    @RequestLine("GET /app")
    Response hello();
}