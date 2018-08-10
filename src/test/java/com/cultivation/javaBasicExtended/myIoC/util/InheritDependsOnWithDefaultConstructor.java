package com.cultivation.javaBasicExtended.myIoC.util;

public class InheritDependsOnWithDefaultConstructor extends DependsOnWithDefaultConstructor {
    @Override
    public String toString() {
        return "Inherited: " + super.toString();
    }
}
