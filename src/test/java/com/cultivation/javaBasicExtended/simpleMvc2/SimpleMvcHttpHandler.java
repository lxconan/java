package com.cultivation.javaBasicExtended.simpleMvc2;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

class SimpleMvcHttpHandler implements HttpHandler {
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
        } catch (Exception error) {
            createSimpleResponse(exchange, 500, error);
            return;
        }

        try {
            createResponse(exchange, context);
        } catch (Exception error) {
            createSimpleResponse(exchange, 503, error);
        }
    }

    @SuppressWarnings("unused")
    private void handleInternal(MyHttpContext context) {
        // TODO: please create response according to test.
        // <--start
        throw new NotImplementedException();
        // --end-->
    }

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

    private void createSimpleResponse(HttpExchange exchange, int statusCode, Exception error) {
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
