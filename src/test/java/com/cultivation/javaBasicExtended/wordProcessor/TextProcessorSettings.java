package com.cultivation.javaBasicExtended.wordProcessor;

class TextProcessorSettings {
    private final int width;
    private final char[] whitespaces;

    public TextProcessorSettings(int width, char[] whitespaces) {
        this.width = width;
        this.whitespaces = whitespaces;
    }

    public int getWidth() {
        return width;
    }

    public char[] getWhitespaces() {
        return whitespaces;
    }

    public boolean isWhitespace(char c) {
        for (char whitespace : whitespaces) {
            if (whitespace == c) {
                return true;
            }
        }

        return false;
    }
}
