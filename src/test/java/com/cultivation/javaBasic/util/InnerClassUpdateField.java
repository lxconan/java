package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public InnerClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen() {
        this.new YearIncrementer().increment();
    }

    @SuppressWarnings("WeakerAccess")
    public class YearIncrementer {
        public void increment() {
            ++InnerClassUpdateField.this.year;
        }
    }
}
