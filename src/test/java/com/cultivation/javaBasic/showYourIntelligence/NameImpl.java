package com.cultivation.javaBasic.showYourIntelligence;

import com.cultivation.javaBasic.util.Person;
import com.cultivation.javaBasic.util.WithName;

public class NameImpl implements WithName, Person {
    @Override
    public String getName() {
        // TODO: please modify the following code to pass the test
        // <--start
        return Person.super.getName();
        // --end-->
    }
}
