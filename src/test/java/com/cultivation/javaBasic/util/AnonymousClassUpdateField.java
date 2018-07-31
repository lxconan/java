package com.cultivation.javaBasic.util;

public class AnonymousClassUpdateField {
    private int year;

    public AnonymousClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    @SuppressWarnings("Convert2Lambda")
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
