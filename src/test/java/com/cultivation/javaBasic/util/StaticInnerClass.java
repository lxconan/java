package com.cultivation.javaBasic.util;

public class StaticInnerClass {

    public Inner createInner() {
        return new Inner("Hello");
    }

    public static class Inner {
        private String name;

        public Inner(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
