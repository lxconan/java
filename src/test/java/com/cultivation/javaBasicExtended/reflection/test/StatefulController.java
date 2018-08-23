package com.cultivation.javaBasicExtended.reflection.test;

import com.cultivation.javaBasicExtended.reflection.framework.Response;

public class StatefulController {
    private String state = "initial status";

    public Response updateStatus() {
        state = "updated status";
        return new Response(200);
    }

    public Response getStatus() {
        return new Response(state, 200);
    }
}

