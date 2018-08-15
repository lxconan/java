package com.cultivation.javaBasicExtended.simpleMvc;

import feign.RequestLine;
import feign.Response;

interface SimpleHttpServerClient {
    @RequestLine("GET /app")
    Response hello();
}
