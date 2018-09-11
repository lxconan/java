package com.cultivation.javaBasicExtended.simpleMvc3;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Response;
import feign.codec.StringDecoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Difficulty(DifficultyLevel.SUPERHARD)
class SimpleHttpServerFacts {
    private SimpleHttpServer server;
    private static final String baseAddress = "http://localhost:4444";

    @BeforeEach
    void init() throws IOException {
        server = new SimpleHttpServer(
            new InetSocketAddress(4444),
            0,
            new MvcApplicationForTest());
        server.start();
    }

    @Test
    void should_get_http_200_for_registered_route() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.getMessage(2);

        assertEquals(200, response.status());
    }

    @Test
    void should_get_http_404_for_unregistered_route() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.getNotExistedResource();

        assertEquals(404, response.status());
    }

    @Test
    void should_get_http_500_for_other_error() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.getNotExistedResource();

        assertEquals(404, response.status());
    }

    @Test
    void should_parse_as_response_code() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.getTeapot();

        assertEquals(418, response.status());
    }

    @Test
    void should_get_http_content_type() {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.getMessage(2);

        Map<String, Collection<String>> headers = response.headers();
        String contentType = headers.get("Content-Type").stream().findFirst().orElse(null);
        assertEquals("application/json", contentType);
    }

    @Test
    void should_get_http_content() throws IOException {
        SimpleHttpServerClient client = Feign.builder()
            .decoder(new StringDecoder())
            .target(SimpleHttpServerClient.class, baseAddress);
        Response response = client.getMessage(2);

        ObjectMapper mapper = new ObjectMapper();
        MessageDto message = mapper.readValue(response.body().asReader(), MessageDto.class);

        assertEquals("2", message.getId());
        assertEquals("Hello", message.getMessage());
    }

    @AfterEach
    void tearDown() {
        server.close();
    }
}

class MvcApplicationForTest extends MyMvcApplication {
    MvcApplicationForTest() {
        registerRoute("/message/[0-9]+", MessageController.class, "getById");
        registerRoute("/message/i_am_a_teapot", MessageController.class, "willThrow418");
    }
}

@SuppressWarnings("unused")
class MessageController implements Controller {
    public MessageDto getById(MyHttpRequest request) {
        URI uri = request.getURI();
        String path = uri.getPath();
        int lastSlash = path.lastIndexOf("/");
        if (lastSlash == -1) {
            throw new MyHttpException(400);
        }

        String id = (lastSlash < path.length() - 1) ? path.substring(lastSlash + 1) : "";

        MessageDto messageDto = new MessageDto();
        messageDto.setId(id);
        messageDto.setMessage("Hello");

        return messageDto;
    }

    public Object willThrow418(MyHttpRequest request) {
        throw new MyHttpException(418);
    }
}

@SuppressWarnings("WeakerAccess")
class MessageDto {
    private String id;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}