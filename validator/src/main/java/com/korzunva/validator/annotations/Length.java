package com.korzunva.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation mark field which value's length
 * must be grater than {@link Length#length()}
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

    /**
     * @return min length of the field's value
     */
    int length() default 0;

    /**
     * @return error message if validation failed
     */
    String message() default "";
}
