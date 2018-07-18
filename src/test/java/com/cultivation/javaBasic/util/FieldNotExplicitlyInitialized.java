package com.cultivation.javaBasic.util;

import java.time.LocalDate;

@SuppressWarnings("ALL")
public class FieldNotExplicitlyInitialized {
    private String name;
    private int yearOfBirth;
    private LocalDate registeredDate;

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }
}
