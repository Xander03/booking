package com.korzunva.dispatcher.processors;

import com.korzunva.dispatcher.annotation.Delete;
import com.korzunva.dispatcher.annotation.Get;
import com.korzunva.dispatcher.annotation.Patch;
import com.korzunva.dispatcher.annotation.Post;
import com.korzunva.dispatcher.annotation.Put;
import com.korzunva.dispatcher.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * MethodProcessor analysis
 * <ul>
 *     <li>{@link Get}</li>
 *     <li>{@link Post}</li>
 *     <li>{@link Put}</li>
 *     <li>{@link Delete}</li>
 *     <li>{@link Patch}</li>
 * </ul>
 * annotations
 */
public class MethodProcessor {

    private static class Key {
        private String url;
        private String method;

        public Key(String url, String method) {
            this.url = url.toUpperCase();
            this.method = method.toUpperCase();
        }

        public String getUrl() {
            return url;
        }

        public String getMethod() {
            return method;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(url, key.url) &&
                    Objects.equals(method, key.method);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, method);
        }

    }

    /**Keeps all controllers' methods*/
    private static Map<Key, Method> methodMap = new HashMap<>();
    /**Keeps all available controllers' methods annotations*/
    private static final Map<Class<? extends Annotation>, String> methodsAnnotations;
    static {
        methodsAnnotations = new HashMap<>();
        methodsAnnotations.put(Get.class, "GET");
        methodsAnnotations.put(Post.class, "POST");
        methodsAnnotations.put(Put.class, "PUT");
        methodsAnnotations.put(Delete.class, "DELETE");
        methodsAnnotations.put(Patch.class, "PATCH");
    }

    /**Returns Method object from methodMap by HTTP method and request Url
     * @param method HTTP method
     * @param url request url*/
    public static Method getMethod(String method, String url) {
        method = method.toUpperCase();
        url = url.toUpperCase();
        for (Key key : methodMap.keySet()) {
            if (key.getMethod().equalsIgnoreCase(method)) {
                String methodUrl = parsePath(key.url);
                if (url.matches(methodUrl)) {
                    return methodMap.get(key);
                }
            }
        }
        return null;
    }

    /**Returns url of the method taken from @RequestMapping annotation
     * @param method target method*/
    static String getMethodPath(Method method) {
        String controllerPath = ControllerProcessor.getControllerPath(method.getDeclaringClass());
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        String methodPath = controllerPath;
        if (annotation != null) {
            methodPath += annotation.value();
        }
        return methodPath;
    }

    /**Returns all method in the controller annotated with method annotations
     * @param controller controller for scan*/
    static void getMethods(Class<?> controller) {
        List<Method> methods = getAnnotatedMethods(controller);
        methods.forEach(method -> {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation: annotations) {
                String httpMethod = methodsAnnotations.get(annotation.annotationType());
                if (httpMethod != null) {
                    String methodUrl = getMethodPath(method);
                    methodMap.put(new Key(methodUrl, httpMethod), method);
                }
            }
        });
    }

    private static String parsePath(String path) {
        if (!path.equals("")) {
            return path.replaceAll("\\{[\\-\\w]+}", "[\\\\-\\\\w]+");
        }
        return path;
    }

    private static List<Method> getAnnotatedMethods(Class<?> controller) {
        Method[] methods = controller.getDeclaredMethods();
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method: methods) {
            if (!method.isBridge() && method.getDeclaredAnnotations().length > 0) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

}
