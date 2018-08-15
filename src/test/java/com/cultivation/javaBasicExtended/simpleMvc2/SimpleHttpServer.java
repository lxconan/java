package com.cultivation.javaBasicExtended.simpleMvc2;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.Closeable;
import java.io.IOException;
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
        handler = new SimpleMvcHttpHandler();
    }

    public void start() throws IOException {
        server = HttpServer.create(getInetSocketAddress(), getConcurrentRequestNumber());
        server.createContext("/", getHandler());
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