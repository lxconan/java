package com.cultivation.javaBasicExtended.printMatchedPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MyGrep {
    @SuppressWarnings("WeakerAccess")
    public static String[] grep(Reader reader, String pattern) throws IOException {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (reader == null || pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException();
        }

        BufferedReader bufferedReader = new BufferedReader(reader);

        Pattern compiled = Pattern.compile(pattern);
        List<String> result = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Matcher matcher = compiled.matcher(line);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                result.add(line.substring(start, end));
            }
        }

        return result.toArray(new String[0]);
        // --end-->
    }
}
