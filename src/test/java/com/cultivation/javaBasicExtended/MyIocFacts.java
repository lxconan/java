package com.cultivation.javaBasicExtended;

import com.cultivation.javaBasicExtended.showYourIntelligence.myIoC.MyIocContext;
import com.cultivation.javaBasicExtended.util.DependsOnWithDefaultConstructor;
import com.cultivation.javaBasicExtended.util.WithDefaultConstructor;
import com.cultivation.javaBasicExtended.util.WithNonParameterizedConstructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void should_create_new_object_per_resolve() {
        MyIocContext iocContext = new MyIocContext();
        iocContext.registerBean(DependsOnWithDefaultConstructor.class);
        iocContext.registerBean(WithDefaultConstructor.class);

        DependsOnWithDefaultConstructor bean = iocContext.getBean(DependsOnWithDefaultConstructor.class);
        DependsOnWithDefaultConstructor anotherBean = iocContext.getBean(DependsOnWithDefaultConstructor.class);

        assertNotSame(bean, anotherBean);
        assertNotSame(bean.getDependent(), anotherBean.getDependent());
    }
}
