package com.cultivation.javaBasicExtended.reflection.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        if (!Modifier.isPublic(action.getModifiers())) {
            return new Response(403);
        }

        Class<?> returnType = action.getReturnType();
        if (returnType != Response.class) {
            return new Response(503);
        }

        if (action.getParameterCount() > 0) {
            return new Response(503);
        }

        try {
            Object controller = registeredControllers.get(requestController).newInstance();
            return (Response) action.invoke(controller);
        } catch (IllegalAccessException e) {
            return new Response(403);
        } catch (InvocationTargetException | InstantiationException e) {
            return new Response(500);
        }
    }

    private Method getControllerMethod(String requestController, String requestMethod) {
        Class<?> controllerClazz = registeredControllers.get(requestController);

        Method[] declaredMethods = controllerClazz.getDeclaredMethods();
        Optional<Method> matchedMethod = Arrays.stream(declaredMethods)
            .filter(clazz -> clazz.getName().equals(requestMethod))
            .findFirst();
        return matchedMethod.orElse(null);
    }
}