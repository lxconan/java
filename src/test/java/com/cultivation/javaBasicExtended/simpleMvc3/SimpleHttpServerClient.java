package com.cultivation.javaBasicExtended.simpleMvc3;

import feign.Param;
import feign.RequestLine;
import feign.Response;

interface SimpleHttpServerClient {
    @RequestLine("GET /message/{id}")
    Response getMessage(@Param("id") int id);

    @RequestLine("GET /message/wtf")
    Response getNotExistedResource();

    @RequestLine("GET /message/i_am_a_teapot")
    Response getTeapot();
}