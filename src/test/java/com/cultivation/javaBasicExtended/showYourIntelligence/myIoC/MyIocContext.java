package com.cultivation.javaBasicExtended.showYourIntelligence.myIoC;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class MyIocContext {
    private final Map<Class, BiFunction> definitions = new HashMap<>();
    private static final ReflectiveResolver<MyIocContext> defaultResolver = new ReflectiveResolver<>();

    public void registerBean(Class<?> beanClazz) {
        if (beanClazz == null) throw new IllegalArgumentException();
        // TODO: please implement the method to register bean class definition.
        // <--start
        if (!containsNoDefaultConstructor(beanClazz)) {
            throw new IllegalArgumentException("You class contains no default constructor");
        }
        definitions.put(beanClazz, defaultResolver);
        // --end-->
    }

    // TODO: You can add some helper methods to improve readability.
    // <--start
    private boolean containsNoDefaultConstructor(Class<?> beanClazz) {
        Constructor<?>[] constructors = beanClazz.getConstructors();
        if (constructors.length == 0) { return false; }

        long count = Arrays.stream(constructors)
            .filter(c -> c.getParameterCount() == 0)
            .count();

        return count > 0;
    }
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
        Objects.requireNonNull(clazz);
        try {
            return resolveBean(context, clazz);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException("Cannot resolve " + clazz.toString(), e);
        }
    }

    private Object resolveBean(T context, Class clazz) throws IllegalAccessException, InstantiationException {
        Object instance = clazz.newInstance();
        Field[] fields = Arrays.stream(clazz.getDeclaredFields())
            .filter(f -> f.getDeclaredAnnotation(MyIoCInjection.class) != null)
            .toArray(Field[]::new);
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            Object fieldInstance = context.getBean(fieldType);
            field.set(instance, fieldInstance);
        }

        return instance;
    }
    // --end-->
}