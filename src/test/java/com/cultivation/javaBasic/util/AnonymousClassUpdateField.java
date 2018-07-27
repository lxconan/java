package com.cultivation.javaBasic.util;

import org.junit.Test;

public class AnonymousClassUpdateField {
    private int year;

    public AnonymousClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    @SuppressWarnings("Convert2Lambda")
    @Test
    public void somethingHappen() {
        Runnable increment = new Runnable() {
            @Override
            public void run() {
                year++;
            }
        };

        increment.run();
    }
}
