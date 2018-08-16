package com.cultivation.javaBasicExtended.simpleMvc3;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class MyMvcApplication extends SimpleMvcHttpHandler {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final Map<String, Route> routes = new HashMap<>();

    @SuppressWarnings({"SameParameterValue", "unused"})
    void registerRoute(
        String pattern,
        Class<? extends Controller> controllerClazz,
        String actionName) {
        // TODO: please implement the following code to pass the test
        // <--start
        throw new NotImplementedException();
        // --end-->
    }

    @SuppressWarnings("unused")
    @Override
    Route getRoute(MyHttpRequest request) {
        URI uri = request.getURI();
        String path = uri.getPath();

        // TODO: please implement the following code to pass the test
        // <--start
        throw new NotImplementedException();
        // --end-->
    }
}
