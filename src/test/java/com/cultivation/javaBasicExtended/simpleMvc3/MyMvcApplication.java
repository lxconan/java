package com.cultivation.javaBasicExtended.simpleMvc3;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class MyMvcApplication extends SimpleMvcHttpHandler {
    private final Map<String, Route> routes = new HashMap<>();

    @SuppressWarnings("SameParameterValue")
    void registerRoute(
        String pattern,
        Class<? extends Controller> controllerClazz,
        String actionName) {
        // TODO: please implement the following code to pass the test
        // <--start
        routes.put(pattern, new RegexRoute(pattern, controllerClazz, actionName));
        // --end-->
    }

    @Override
    Route getRoute(MyHttpRequest request) {
        URI uri = request.getURI();
        String path = uri.getPath();

        // TODO: please implement the following code to pass the test
        // <--start
        Optional<Route> matchedRoute = routes.values().stream()
            .filter(r -> r.isMatch(path))
            .findFirst();
        return matchedRoute.orElse(null);
        // --end-->
    }
}
