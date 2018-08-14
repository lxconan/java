package com.cultivation.javaBasicExtended.reverseString;

import java.util.Stack;
import java.util.StringTokenizer;

class StringReverser {
    @SuppressWarnings("WeakerAccess")
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null) throw new IllegalArgumentException();

        StringTokenizer tokenizer = new StringTokenizer(input);

        Stack<String> storage = new Stack<>();
        while (tokenizer.hasMoreTokens()) {
            storage.push(tokenizer.nextToken());
        }

        int size = storage.size();
        String[] result = new String[size];
        for (int i = 0; i < size; ++i) {
            result[i] = storage.pop();
        }

        return result;

        // --end-->
    }
}
