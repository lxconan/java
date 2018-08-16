package com.cultivation.javaBasicExtended.simpleMvc3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

interface MyHttpRequest {
    String getMethod();
    URI getURI();
    Optional<String> getHeaderValue(String key);
    List<String> getHeaderValues(String key);
    Optional<Integer> getContentLength();
    Optional<String> getContentType();
    InputStream readAsInputStream();
    String readAsString(Charset charset) throws IOException;
    <T> T readAs(Class<T> clazz, Charset charset) throws IOException;
}

class MyHttpRequestImpl implements MyHttpRequest {
    private final HttpExchange exchange;
    private final Map<String, List<String>> queries;

    MyHttpRequestImpl(HttpExchange exchange) {
        this.exchange = exchange;
        queries = initializeQueryString();
    }

    private Map<String, List<String>> initializeQueryString() {
        URI uri = getURI();
        return URLEncodedUtils.parse(uri, StandardCharsets.UTF_8).stream()
            .collect(Collectors.groupingBy(
                pair -> pair.getName().toLowerCase(),
                Collectors.mapping(NameValuePair::getValue, Collectors.toList())
            ));
    }

    @Override
    public String getMethod() {
        return exchange.getRequestMethod();
    }

    @Override
    public URI getURI() {
        return exchange.getRequestURI();
    }

    @Override
    public Optional<String> getHeaderValue(String key) {
        List<String> values = queries.get(key);
        if (values == null) { return Optional.empty(); }
        return values.stream().findFirst();
    }

    @Override
    public List<String> getHeaderValues(String key) {
        List<String> values = queries.get(key);
        return values == null ? Collections.emptyList() : values;
    }

    @Override
    public Optional<Integer> getContentLength() {
        Optional<String> contentLengthHeaderValue = getHeaderValue("content-length");
        if (!contentLengthHeaderValue.isPresent()) return Optional.empty();
        String contentLengthString = contentLengthHeaderValue.get();

        if (contentLengthString.isEmpty()) { return Optional.empty(); }

        try {
            return Optional.of(Integer.parseInt(contentLengthString));
        } catch (NumberFormatException formatError) {
            System.out.println("[ERROR] format error for content-length header: " + contentLengthString);
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getContentType() {
        return getHeaderValue("content-type");
    }

    @Override
    public InputStream readAsInputStream() {
        return exchange.getRequestBody();
    }

    @Override
    public String readAsString(Charset charset) throws IOException {
        InputStream stream = readAsInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
        return readAsChunkedString(reader);
    }

    @Override
    public <T> T readAs(Class<T> clazz, Charset charset) throws IOException {
        String s = readAsString(charset);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(s, clazz);
    }

    private String readAsChunkedString(BufferedReader reader) throws IOException {
        int nextChar;
        StringBuilder builder = new StringBuilder();
        while ((nextChar = reader.read()) != -1) {
            builder.append((char)nextChar);
        }

        return builder.toString();
    }
}