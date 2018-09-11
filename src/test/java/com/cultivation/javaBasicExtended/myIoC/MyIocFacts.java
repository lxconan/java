package com.cultivation.javaBasicExtended.myIoC;

import com.cultivation.javaBasicExtended.Difficulty;
import com.cultivation.javaBasicExtended.DifficultyLevel;
import com.cultivation.javaBasicExtended.myIoC.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Difficulty(DifficultyLevel.SUPERHARD)
class MyIocFacts {
    @Test
    void should_create_object_with_default_constructor() {
        MyIocContext iocContext = new MyIocContext();
        iocContext.registerBean(WithDefaultConstructor.class);
        WithDefaultConstructor bean = iocContext.getBean(WithDefaultConstructor.class);

        assertEquals("WithDefaultConstructor", bean.toString());
    }

    @Test
    void should_create_object_with_declared_no_argument_constructor() {
        MyIocContext iocContext = new MyIocContext();
        iocContext.registerBean(WithNonParameterizedConstructor.class);
        WithNonParameterizedConstructor bean = iocContext.getBean(WithNonParameterizedConstructor.class);

        assertEquals("WithNonParameterizedConstructor", bean.toString());
    }

    @Test
    void should_create_object_with_dependency() {
        MyIocContext iocContext = new MyIocContext();
        iocContext.registerBean(DependsOnWithDefaultConstructor.class);
        iocContext.registerBean(WithDefaultConstructor.class);

        DependsOnWithDefaultConstructor bean = iocContext.getBean(DependsOnWithDefaultConstructor.class);

        assertEquals("DependsOnWithDefaultConstructor depends on WithDefaultConstructor", bean.toString());
    }

    @Test
    void should_support_subclass() {
        MyIocContext iocContext = new MyIocContext();
        iocContext.registerBean(InheritDependsOnWithDefaultConstructor.class);
        iocContext.registerBean(WithDefaultConstructor.class);

        InheritDependsOnWithDefaultConstructor bean = iocContext.getBean(InheritDependsOnWithDefaultConstructor.class);

        assertEquals(
            "Inherited: DependsOnWithDefaultConstructor depends on WithDefaultConstructor",
            bean.toString()
        );
    }

    @Test
    void should_create_new_object_per_resolve() {
        MyIocContext iocContext = new MyIocContext();
        iocContext.registerBean(DependsOnWithDefaultConstructor.class);
        iocContext.registerBean(WithDefaultConstructor.class);

        DependsOnWithDefaultConstructor bean = iocContext.getBean(DependsOnWithDefaultConstructor.class);
        DependsOnWithDefaultConstructor anotherBean = iocContext.getBean(DependsOnWithDefaultConstructor.class);

        assertNotSame(bean, anotherBean);
        assertNotSame(bean.getDependent(), anotherBean.getDependent());
    }

    @Test
    void should_not_support_object_without_parameter_less_constructor() {
        MyIocContext iocContext = new MyIocContext();

        assertThrows(
            IllegalArgumentException.class,
            () -> iocContext.registerBean(WithParameterizedConstructor.class));
    }

    @Test
    void should_throw_if_cannot_resolve() {
        MyIocContext iocContext = new MyIocContext();

        assertThrows(
            IllegalArgumentException.class,
            () -> iocContext.getBean(WithDefaultConstructor.class)
        );
    }

    @Test
    void should_throw_if_cannot_resolve_dependency() {
        MyIocContext iocContext = new MyIocContext();

        iocContext.registerBean(DependsOnWithDefaultConstructor.class);

        assertThrows(
            IllegalArgumentException.class,
            () -> iocContext.getBean(DependsOnWithDefaultConstructor.class)
        );
    }

    @Test
    void should_throw_if_class_is_null() {
        MyIocContext iocContext = new MyIocContext();

        assertThrows(
            IllegalArgumentException.class,
            () -> iocContext.getBean(null)
        );
    }

    @Test
    void should_throw_if_class_is_array() {
        MyIocContext iocContext = new MyIocContext();

        assertThrows(
            IllegalArgumentException.class,
            () -> iocContext.registerBean(Object[].class)
        );
    }

    @Test
    void should_throw_if_class_is_void() {
        MyIocContext iocContext = new MyIocContext();

        assertThrows(
            IllegalArgumentException.class,
            () -> iocContext.registerBean(Void.class)
        );
    }
}
