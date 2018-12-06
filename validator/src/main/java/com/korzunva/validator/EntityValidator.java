package com.korzunva.validator;

import com.korzunva.validator.annotations.Length;
import com.korzunva.validator.annotations.NotNull;
import com.korzunva.validator.annotations.Range;
import com.korzunva.validator.impl.LengthValidatorImpl;
import com.korzunva.validator.impl.NotNullValidatorImpl;
import com.korzunva.validator.impl.RangeValidatorImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main validation service
 */
public class EntityValidator {

    private static final String CANT_GET_FIELD_VALUE = "Can't get field '%s' value";
    private static final String NULL_OBJECT_FOR_VALIDATION = "Object for validation can't be null";

    private static final List<Class> validationAnnotations = new ArrayList<>();
    private static final Map<Class, Validator> validators = new HashMap<>();
    static {
        validationAnnotations.add(NotNull.class);
        validationAnnotations.add(Length.class);
        validationAnnotations.add(Range.class);

        validators.put(NotNull.class, new NotNullValidatorImpl());
        validators.put(Length.class, new LengthValidatorImpl());
        validators.put(Range.class, new RangeValidatorImpl());
    }

    private static List<Field> getAnnotatedFields(Object obj) {
        List<Field> fields = Arrays.asList(obj.getClass().getDeclaredFields());
        return fields.stream()
                .filter(field -> getValidationAnnotations(field).size() > 0)
                .collect(Collectors.toList());
    }

    /**
     * Performs main validation object
     * @param obj object to be validated
     * @return list of errors
     */
    public static <Entity extends Validatable>  List<String> validate(Entity obj) {
        List<String> errors = new ArrayList<>();

        if (obj == null) {
            errors.add(NULL_OBJECT_FOR_VALIDATION);
            return errors;
        }

        List<Field> fields = getAnnotatedFields(obj);
        fields.forEach(field ->
                getValidationAnnotations(field).forEach(annotation -> {
                    Validator validator = validators.get(annotation.annotationType());
                    try {
                        String error = validator.validate(getFieldValue(field, obj), annotation);
                        if (error != null) {
                            errors.add(String.format(error, field.getName()));
                        }
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        errors.add(String.format(CANT_GET_FIELD_VALUE, field.getName()));
                    }
                })
        );
        return errors;
    }

    private static List<Annotation> getValidationAnnotations(Field field) {
        return Arrays.stream(field.getAnnotations())
                .filter(annotation -> validationAnnotations.contains(annotation.annotationType()))
                .collect(Collectors.toList());
    }

    private static Object getFieldValue(Field field, Object obj)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "get" + getFieldName(field);
        Class<?> objectClass = obj.getClass();
        Method getter = objectClass.getDeclaredMethod(methodName);
        return getter.invoke(obj);
    }

    private static String getFieldName(Field field) {
        char[] fieldName = field.getName().toCharArray();
        fieldName[0] = Character.toUpperCase(fieldName[0]);
        return new String(fieldName);
    }

}
