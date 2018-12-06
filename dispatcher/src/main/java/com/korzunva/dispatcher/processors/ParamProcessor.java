package com.korzunva.dispatcher.processors;


import com.korzunva.dispatcher.annotation.PathParam;
import com.korzunva.dispatcher.annotation.QueryParam;
import com.korzunva.dispatcher.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ParamProcessor method params
 */
public class ParamProcessor {

    /**Keeps annotation for different types of controller method's parameters*/
    private static final List<Class<?>> paramAnnotations;
    static {
        paramAnnotations = new ArrayList<>();
        paramAnnotations.add(PathParam.class);
        paramAnnotations.add(QueryParam.class);
        paramAnnotations.add(RequestBody.class);
    }

    /**Returns an array that contains values of method params taken from HttpServletRequest
     * @param request HttpServlet from which params will be taken
     * @param method Method which params searched in request
     * @throws IOException if some error occurs while reading the request*/
    public static Object[] getParams(HttpServletRequest request, Method method) throws IOException {
        List<Parameter> parameters = getAnnotatedParams(method);
        List<Object> paramValues = new ArrayList<>(parameters.size());
        for (Parameter parameter : parameters) {
            Class<?> paramAnnotation = getParamAnnotation(parameter);
            Class paramClass = parameter.getType();

            if (paramAnnotation.equals(PathParam.class)) {
                String pathParam = PathParamProcessor.getValues(
                        request, MethodProcessor.getMethodPath(method), parameter.getName());
                paramValues.add(convertString(paramClass, pathParam));
            }

            if (paramAnnotation.equals(QueryParam.class)) {
                String queryParam = QueryParamProcessor.getValue(request, parameter.getName());
                paramValues.add(convertString(paramClass, queryParam));
            }

            if (paramAnnotation.equals(RequestBody.class)) {
                paramValues.add(RequestBodyProcessor.getValue(request, parameter));
            }

        }

        return paramValues.toArray();
    }

    private static Class<?> getParamAnnotation(Parameter parameter) {
        Annotation[] annotations = parameter.getAnnotations();
        for (Annotation annotation : annotations) {
            if (paramAnnotations.contains(annotation.annotationType())) {
                return annotation.annotationType();
            }
        }
        return null;
    }

    private static List<Parameter> getAnnotatedParams(Method method) {
        Parameter[] parameters = method.getParameters();
        return Arrays.stream(parameters).filter(parameter ->
                getParamAnnotation(parameter) != null
        ).collect(Collectors.toList());
    }

    private static Object convertString(Class clazz, String value) {
        if (value != null) {
            if (Boolean.class == clazz) return Boolean.parseBoolean(value);
            if (Byte.class == clazz) return Byte.parseByte(value);
            if (Short.class == clazz) return Short.parseShort(value);
            if (Integer.class == clazz) return Integer.parseInt(value);
            if (Long.class == clazz) return Long.parseLong(value);
            if (Float.class == clazz) return Float.parseFloat(value);
            if (Double.class == clazz) return Double.parseDouble(value);
        }
        return value;
    }

}
