package com.cultivation.javaBasic.util;

public enum EscapedChars {
    BACKSPACE((char)0x0008),
    TAB((char)0x0009),
    LINE_FEED((char)0x000a),
    CARRIAGE_RETURN((char) 0x000d),
    DOUBLE_QUOTE((char) 0x0022),
    SINGLE_QUOTE((char) 0x0027),
    BACKSLASH((char) 0x005c);

    private final char value;

    EscapedChars(char value) {
        this.value = value;
    }

    public char getValue() { return value; }
}
