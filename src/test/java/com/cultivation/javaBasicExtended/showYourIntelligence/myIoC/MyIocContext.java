package com.cultivation.javaBasicExtended.showYourIntelligence.myIoC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class MyIocContext {
    private final Map<Class, BiFunction> definitions = new HashMap<>();
    private static final ReflectiveResolver<MyIocContext> defaultResolver = new ReflectiveResolver<>();

    public void registerBean(Class<?> beanClazz) {
        Objects.requireNonNull(beanClazz);
        definitions.put(beanClazz, defaultResolver);
    }

    public <T> T getBean(Class<T> clazz) {
        Objects.requireNonNull(clazz);
        BiFunction creator = definitions.get(clazz);
        if (creator == null) {
            throw new IllegalArgumentException("Cannot find type" + clazz.toString());
        }

        //noinspection unchecked
        return (T) creator.apply(this, clazz);
    }
}

class ReflectiveResolver<T extends MyIocContext> implements BiFunction<T, Class, Object> {
    // TODO: Use reflection to create instance of `T`. You can add helper methods if you like.
    // <--start
    @Override
    public Object apply(T context, Class clazz) {
        Objects.requireNonNull(clazz);

        Constructor[] constructors = clazz.getDeclaredConstructors();
        if (constructors.length == 0) {
            return resolveBeanViaDefaultConstructor(clazz);
        }

        if (constructors.length != 1) {
            throw new IllegalStateException("I do not know which constructor to call");
        }

        return resolveBean(context, constructors[0]);
    }

    private Object resolveBean(T context, Constructor constructor) {
        if (constructor.isVarArgs()) {
            throw new IllegalStateException("Do not support var args");
        }

        Parameter[] parameters = constructor.getParameters();
        if (parameters.length == 0) {
            try {
                return constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException("Error occurred while resolving instance.", e);
            }
        }

        Object[] arguments = Arrays.stream(parameters)
            .map(p -> context.getBean(p.getType()))
            .toArray();

        try {
            return constructor.newInstance(arguments);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Error occured whiule resolving instance.", e);
        }
    }

    private Object resolveBeanViaDefaultConstructor(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Cannot instantiate via default constructor", e);
        }
    }
    // --end-->
}