package com.cultivation.javaBasicExtended.simpleMvc;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@SuppressWarnings("WeakerAccess")
class SimpleHttpServer implements Closeable {
    private final InetSocketAddress inetSocketAddress;
    private final int concurrentRequestNumber;
    private HttpServer server;
    private final HttpHandler handler;

    public SimpleHttpServer(
        InetSocketAddress inetSocketAddress,
        int concurrentRequestNumber) {
        this.inetSocketAddress = inetSocketAddress;
        this.concurrentRequestNumber = concurrentRequestNumber;
        handler = exchange -> {
            // TODO: please create HTTP 200 response with content "Hello" (without quotes)
            // <--start
            byte[] content = "Hello".getBytes();
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.add("Content-Type", "text/plain");

            exchange.sendResponseHeaders(200, content.length);

            OutputStream stream = exchange.getResponseBody();
            stream.write(content);
            stream.close();
            exchange.close();
            // --end-->
        };
    }

    public void start() throws IOException {
        server = HttpServer.create(getInetSocketAddress(), getConcurrentRequestNumber());
        server.createContext("/app", getHandler());
        server.setExecutor(null);
        server.start();
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public int getConcurrentRequestNumber() {
        return concurrentRequestNumber;
    }

    public HttpHandler getHandler() {
        return handler;
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
