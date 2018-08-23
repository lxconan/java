package com.cultivation.javaBasicExtended.reflection.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class MyAppFramework {
    private final Map<String, Class> registeredControllers = new HashMap<>();

    public void registerController(Class controllerClazz) {
        if (controllerClazz == null) {throw new IllegalArgumentException("controller clazz must be provided.");}
        String controllerKey = getControllerClazzKey(controllerClazz);
        if (registeredControllers.containsKey(controllerKey)) {
            throw new IllegalArgumentException(
                String.format("The controller %s has already been registered.", controllerKey));
        }

        registeredControllers.put(controllerKey, controllerClazz);
    }

    private String getControllerClazzKey(Class controllerClazz) {
        return controllerClazz.getName();
    }

    public Response getResponse(String requestController, String requestMethod) {
        if (requestController == null || requestMethod == null) {
            throw new IllegalArgumentException("The request controller and request method must be provided");
        }

        if (!registeredControllers.containsKey(requestController)) {
            return new Response(null, 404);
        }

        Method action = getControllerMethod(requestController, requestMethod);
        if (action == null) {
            return new Response(null, 404);
        }

        return invokeAction(requestController, action);
    }

    private Response invokeAction(String requestController, Method action) {
        try {
            Object controller = registeredControllers.get(requestController).newInstance();
            return (Response) action.invoke(controller);
        } catch (IllegalAccessException e) {
            return new Response(503);
        } catch (InvocationTargetException | InstantiationException e) {
            return new Response(500);
        }
    }

    private Method getControllerMethod(String requestController, String requestMethod) {
        Class<?> controllerClazz = registeredControllers.get(requestController);
        try {
            Method declaredMethod = controllerClazz.getDeclaredMethod(requestMethod);
            int modifiers = declaredMethod.getModifiers();
            if (!Modifier.isPublic(modifiers)) { return null; }
            return declaredMethod;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}