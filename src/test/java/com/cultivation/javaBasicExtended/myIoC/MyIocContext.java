package com.cultivation.javaBasicExtended.myIoC;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@SuppressWarnings({"WeakerAccess", "MismatchedQueryAndUpdateOfCollection", "unused"})
public class MyIocContext {
    private final Map<Class, BiFunction> definitions = new HashMap<>();
    private static final ReflectiveResolver<MyIocContext> defaultResolver = new ReflectiveResolver<>();

    public void registerBean(Class<?> beanClazz) {
        if (beanClazz == null) throw new IllegalArgumentException();
        // TODO: please implement the method to register bean class definition.
        // <--start
        throw new NotImplementedException();
        // --end-->
    }

    // TODO: You can add some helper methods to improve readability.
    // <--start


    // --end-->

    public <T> T getBean(Class<T> beanClazz) {
        if (beanClazz == null) throw new IllegalArgumentException();
        BiFunction creator = definitions.get(beanClazz);
        if (creator == null) {
            throw new IllegalArgumentException("Cannot find type" + beanClazz.toString());
        }

        //noinspection unchecked
        return (T) creator.apply(this, beanClazz);
    }
}

class ReflectiveResolver<T extends MyIocContext> implements BiFunction<T, Class, Object> {
    // TODO: Use reflection to create instance of `T`. You can add helper methods if you like.
    // <--start
    @Override
    public Object apply(T context, Class clazz) {
        throw new NotImplementedException();
    }
    // --end-->
}