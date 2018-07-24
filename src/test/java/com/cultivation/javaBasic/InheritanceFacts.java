package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InheritanceFacts {

    @Test
    public void should_be_derived_from_object_class() {
        // TODO: please modify the following code to pass the test
        // <--start
        final Class<?> expectedSuperClass = Object.class;
        // --end-->

        assertEquals(expectedSuperClass, SimpleEmptyClass.class.getSuperclass());
    }

    @Test
    public void should_call_super_class_constructor() {
        DerivedFromSuperClassWithDefaultConstructor instance = new DerivedFromSuperClassWithDefaultConstructor();

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {
            "SuperClassWithDefaultConstructor.constructor()",
            "DerivedFromSuperClassWithDefaultConstructor.constructor()"
        };
        // --end-->

        String[] logs = instance.getLogs();

        assertArrayEquals(expected, logs);
    }

    @Test
    public void should_call_super_class_constructor_continued() {
        DerivedFromSuperClassWithDefaultConstructor instance = new DerivedFromSuperClassWithDefaultConstructor(42);

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {
            "SuperClassWithDefaultConstructor.constructor()",
            "DerivedFromSuperClassWithDefaultConstructor.constructor()",
            "DerivedFromSuperClassWithDefaultConstructor.constructor(int)"
        };
        // --end-->

        String[] logs = instance.getLogs();

        assertArrayEquals(expected, logs);
    }

    @Test
    public void should_call_super_class_constructor_more() {
        DerivedFromSuperClassWithDefaultConstructor instance = new DerivedFromSuperClassWithDefaultConstructor("God");

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {
            "SuperClassWithDefaultConstructor.constructor(String)",
            "DerivedFromSuperClassWithDefaultConstructor.constructor(String)"
        };
        // --end-->

        String[] logs = instance.getLogs();

        assertArrayEquals(expected, logs);
    }

    @Test
    public void should_call_most_derived_methods() {
        BaseClassForOverriding instance = new DerivedFromBaseClassForOverriding();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expectedName = "DerivedFromBaseClassForOverriding";
        // --end-->

        assertEquals(expectedName, instance.getName());
    }

    @Test
    public void should_call_super_class_methods() {
        DerivedFromBaseClassForOverridingCallingSuper instance = new DerivedFromBaseClassForOverridingCallingSuper();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expectedName = "BaseClassForOverriding->DerivedFromBaseClassForOverridingCallingSuper";
        // --end-->

        assertEquals(expectedName, instance.getName());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void should_use_caution_when_dealing_with_array_type() {
        DerivedFromSuperClassWithDefaultConstructor[] array = new DerivedFromSuperClassWithDefaultConstructor[4];
        SuperClassWithDefaultConstructor[] arrayWithBaseType = (SuperClassWithDefaultConstructor[])array;

        boolean willThrow = false;

        try {
            arrayWithBaseType[arrayWithBaseType.length - 1] = new SuperClassWithDefaultConstructor();
        } catch (Exception error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Test
    public void should_not_make_you_confused() {
        NestedDerivedClassWithName nested = new NestedDerivedClassWithName();
        DerivedFromBaseClassWithName derived = nested;

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "NestedDerivedClassWithName";
        // --end-->

        assertEquals(expected, derived.getName());
    }

    @Test
    public void should_not_make_you_confused_2() {
        DerivedFromBaseClassWithName derived = new DerivedFromBaseClassWithName();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "BaseClassWithName";
        // --end-->

        assertEquals(expected, derived.getName());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void should_use_instance_of_to_determine_inheritance_relationship() {
        NestedDerivedClassWithName nested = new NestedDerivedClassWithName();

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expectedResult1 = Optional.of(true);
        final Optional<Boolean> expectedResult2 = Optional.of(true);
        final Optional<Boolean> expectedResult3 = Optional.of(true);
        // --end-->

        assertEquals(expectedResult1.get(), nested instanceof NestedDerivedClassWithName);
        assertEquals(expectedResult2.get(), nested instanceof DerivedFromBaseClassWithName);
        assertEquals(expectedResult3.get(), nested instanceof BaseClassWithName);
    }

    @SuppressWarnings({"ConstantConditions", "UnnecessaryBoxing"})
    @Test
    public void should_use_instance_of_only_in_inheritance_relationship() {
        final Object integer = new Integer(42);  // the magic of life

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expectedResult1 = Optional.of(true);
        final Optional<Boolean> expectedResult2 = Optional.of(false);
        // --end-->

        assertEquals(expectedResult1.get(), integer instanceof Integer );
        assertEquals(expectedResult2.get(), integer instanceof Long );
    }
}

/*
 * - If `Derived` derives from `Base` and `Base` class contains one parameterized constructor, then do you think we
 *   should explicitly call constructor in `Base` from `Derived` class?
 * - Why you should override hashCode while overriding equals?
 * - How to write a perfect equals method?
 */
