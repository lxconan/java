package com.cultivation.javaBasic.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class InitializationOrderClass {
    private static List<String> logger = new ArrayList<>();

    public static void resetLogs() {
        logger.clear();
    }

    public static String[] getLogs() {
        return logger.toArray(new String[0]);
    }

    private final Object field = initField();

    private Object initField() {
        logger.add("Field Initializer");
        return null;
    }

    {
        logger.add("Initialization Block");
    }

    public InitializationOrderClass() {
        this(4);
        logger.add("Default constructor");
    }

    public InitializationOrderClass(int argument) {
        logger.add("Constructor with argument");
    }
}
