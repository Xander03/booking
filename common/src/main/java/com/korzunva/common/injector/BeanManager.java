package com.korzunva.common.injector;

import com.korzunva.common.exception.InjectorException;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * BeanManager class contains some methods to perform dependency injection
 */
public enum BeanManager {

    INSTANCE;

    /**
     * diMap contains classes which objects can be injected
     */
    private Map<Key, Class> diMap;
    /**
     * scope contains objects that can be injected
     */
    private Map<Class, Object> scope;

    BeanManager() {
        diMap = new HashMap<>();
        scope = new HashMap<>();
        scanProject();
    }

    /**
     * Helper class used as key in diMap
     */
    private class Key {
        private Class<?> interfaceClass;
        private String qualifier;

        Key(Class<?> interfaceClass, String qualifier) {
            this.interfaceClass = interfaceClass;
            this.qualifier = qualifier;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key that = (Key) o;
            return Objects.equals(interfaceClass, that.interfaceClass)
                    && Objects.equals(qualifier, that.qualifier);
        }

        @Override
        public int hashCode() {
            return Objects.hash(interfaceClass, qualifier);
        }
    }

    /**Scan whole project finding classes which can be injected
     * and saving them in diMap*/
    private void scanProject() {
        Reflections reflections = new Reflections("");
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Component.class);
        for (Class<?> implementationClass : types) {
            for (Class<?> iFace : getAllInterfacesAndSuperClasses(implementationClass)) {
                String qualifier = getQualifier(implementationClass, iFace);
                diMap.put(new Key(iFace, qualifier), implementationClass);
            }
        }
    }

    /**
     * Returns existing object to be injected or creates new
     * @param interfaceClass interface of the class to be injected
     * @param qualifier string that points to needed object of interfaceClass
     * @return existing object to be injected or creates new
     */
    public <C> C getBean(Class<C> interfaceClass, String qualifier) {
        Class<?> implementationClass = diMap.get(new Key(interfaceClass, qualifier));
        if (scope.containsKey(implementationClass)) {
            return (C) scope.get(implementationClass);
        }

        try {
            Constructor constructor = getMaxConstructor(implementationClass);
            Object[] params = Arrays.stream(constructor.getParameterTypes())
                    .map(this::getBean).toArray();
            Object bean = constructor.newInstance(params);

            scope.put(implementationClass, bean);
            return (C) bean;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new InjectorException(e.getMessage());
        }

    }

    /**
     * Returns existing object to be injected or creates new
     * @param interfaceClass interface or base class of the class to be injected
     * @return existing object to be injected or creates new
     */
    public <C> C getBean(Class<C> interfaceClass) {
        String qualifier = interfaceClass.getName();
        return getBean(interfaceClass, qualifier);
    }

    private Constructor getMaxConstructor(Class<?> clazz) {
        Constructor[] constructors = clazz.getConstructors();
        return Arrays.stream(constructors)
                .max(Comparator.comparing(Constructor::getParameterCount))
                .orElse(null);
    }

    private String getQualifier(Class<?> impl, Class<?> base) {
        String qualifier = impl.getAnnotation(Component.class).value();
        if (qualifier.equals("")) {
            qualifier = base.getName();
        }
        return qualifier;
    }

    private Class<?>[] getAllInterfacesAndSuperClasses(Class<?> clazz) {
        List<Class<?>> interfaces = new ArrayList<>();
        if (clazz != null) {
            interfaces.add(clazz);
            interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
            interfaces.addAll(Arrays.asList(getAllInterfacesAndSuperClasses(clazz.getSuperclass())));
        }
        return interfaces.toArray(new Class[0]);
    }

}
