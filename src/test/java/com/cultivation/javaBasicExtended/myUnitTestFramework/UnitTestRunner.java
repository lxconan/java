package com.cultivation.javaBasicExtended.myUnitTestFramework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

class UnitTestRunner {
    UnitTestRunningResult run(Class<?> unitTestClass) {
        if (unitTestClass == null) {
            throw new IllegalArgumentException("Unit test class must be provided.");
        }

        Method[] methods = getTestMethods(unitTestClass);

        Object unitTestObject;
        try {
            unitTestObject = unitTestClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return new UnitTestRunningResult(e.getMessage());
        }

        ArrayList<TestResultItem> details = runUnitTests(unitTestObject, unitTestClass, methods);
        return new UnitTestRunningResult(details);
    }

    private ArrayList<TestResultItem> runUnitTests(Object unitTestObject, Class<?> unitTestClass, Method[] methods) {
        ArrayList<TestResultItem> details = new ArrayList<>();

        String className = unitTestClass.getName();
        for (Method method : methods) {
            method.setAccessible(true);

            String methodName = method.getName();
            try {
                method.invoke(unitTestObject);
            } catch (IllegalAccessException e) {
                details.add(new TestResultItem(false, className, methodName, e.getMessage()));
                continue;
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                details.add(new TestResultItem(false, className, methodName, cause.getMessage()));
                continue;
            }

            details.add(new TestResultItem(true, className, methodName, null));
        }
        return details;
    }

    private Method[] getTestMethods(Class<?> unitTestClass) {
        return Arrays.stream(unitTestClass.getMethods())
            .filter(m -> m.getDeclaredAnnotation(MyTest.class) != null)
            .toArray(Method[]::new);
    }
}
