package com.cultivation.javaBasicExtended.simpleMvc2;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import feign.Feign;
import feign.Response;
import feign.codec.StringDecoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Difficulty(DifficultyLevel.SUPERHARD)
class SimpleHttpServerFacts {
    private SimpleHttpServer server;
    private static final String baseAddress = "http://localhost:4444";

    @BeforeEach
    void init() throws IOException {
        server = new SimpleHttpServer(new InetSocketAddress(4444), 0);
        server.start();
    }

    @Test
    void should_get_http_200_for_app_route() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.hello();

        assertEquals(200, response.status());
    }

    @SuppressWarnings("Duplicates")
    @Test
    void should_get_http_content_type() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.hello();

        Map<String, Collection<String>> headers = response.headers();
        String contentType = headers.get("Content-Type").stream().findFirst().orElse(null);
        assertEquals("text/plain", contentType);
    }

    @Test
    void should_get_http_content() throws IOException {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.hello();

        BufferedReader reader = new BufferedReader(response.body().asReader());
        String message = reader.readLine();
        assertEquals("Hello /message/2", message);
    }

    @AfterEach
    void tearDown() {
        server.close();
    }
}
