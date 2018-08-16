package com.cultivation.javaBasicExtended.simpleMvc3;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Map;

abstract class SimpleMvcHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        MyHttpContext context;

        try {
            context = new MyHttpContext(new MyHttpRequestImpl(exchange), new MyHttpResponseImpl());
        } catch (Exception error) {
            createSimpleResponse(exchange, 503, error);
            return;
        }

        try {
            handleInternal(context);
        } catch (MyHttpException e) {
            createSimpleResponse(exchange, e.getStatusCode(), null);
        } catch (Throwable error) {
            createSimpleResponse(exchange, 500, error);
            return;
        }

        try {
            createResponse(exchange, context);
        } catch (Exception error) {
            createSimpleResponse(exchange, 503, error);
        }
    }

    private void handleInternal(MyHttpContext context) throws Throwable {
        Route route = getRoute(context.getRequest());
        if (route == null) {
            makeNotFoundResponse(context);
            return;
        }

        Class<? extends Controller> controllerClass = route.getControllerClass();
        Controller controller = createController(controllerClass);
        invokeControllerAction(context, controller, controllerClass, route.getActionName());
    }

    private void makeNotFoundResponse(MyHttpContext context) {
        MyHttpResponse response = context.getResponse();
        response.setStatusCode(404);
        response.setBodyBuffer("Not Found".getBytes());
        Map<String, String> headers = response.getHeaders();
        headers.put("Content-Type", "text/plain");
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    private void invokeControllerAction(
        MyHttpContext context,
        Controller controller,
        Class<? extends Controller> controllerClass,
        String actionName) throws Throwable {

        Method method;
        try {
            method = controllerClass.getMethod(actionName, MyHttpRequest.class);
        } catch (NoSuchMethodException e) {
            makeNotFoundResponse(context);
            return;
        }

        try {
            Object result = method.invoke(controller, context.getRequest());

            // TODO: please serialize the returned result to JSON string using Jackson.
            // <--start
            String json = null;
            // --end-->

            makeResponse(context, json);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private void makeResponse(MyHttpContext context, String json) {
        MyHttpResponse response = context.getResponse();
        response.setStatusCode(200);
        response.setBodyBuffer(json.getBytes());
        Map<String, String> headers = response.getHeaders();
        headers.put("Content-Type", "application/json");
    }

    @SuppressWarnings("unused")
    private Controller createController(Class<? extends Controller> controllerClass) {
        // TODO: create a controller class, please note that the controller class will not have any dependency
        // <--start
        throw new NotImplementedException();
        // --end-->
    }

    abstract Route getRoute(MyHttpRequest request);

    private void createResponse(HttpExchange exchange, MyHttpContext context) throws IOException {
        Headers headers = exchange.getResponseHeaders();
        copyResponseHeaders(headers, context);
        exchange.sendResponseHeaders(
            context.getResponse().getStatusCode(),
            context.getResponse().getBodySize());

        OutputStream body = exchange.getResponseBody();
        copyResponseBody(body, context);

        exchange.close();
    }

    private void copyResponseBody(OutputStream body, MyHttpContext context) throws IOException {
        MyHttpResponse response = context.getResponse();
        byte[] buffer = response.getBodyBuffer();
        body.write(buffer);
        body.flush();
    }

    private void copyResponseHeaders(Headers headers, MyHttpContext context) {
        MyHttpResponse response = context.getResponse();
        Map<String, String> responseHeaders = response.getHeaders();
        responseHeaders.forEach(headers::add);
    }

    private void createSimpleResponse(HttpExchange exchange, int statusCode, Throwable error) {
        StringBuilder message = new StringBuilder()
            .append("HTTP Error: ")
            .append(statusCode)
            .append("\n");
        if (error != null) {
            message.append("Details: ").append(error.getMessage());
        }

        byte[] content = message.toString().getBytes(StandardCharsets.UTF_8);
        try {
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.add("Content-Type", "text/plain");
            exchange.sendResponseHeaders(statusCode, content.length);

            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(content);
        } catch (IOException e) {
            System.out.println("Fail to send response." + e.getMessage());
        } finally {
            exchange.close();
        }
    }
}