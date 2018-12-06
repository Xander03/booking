package com.korzunva.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation mark field which value mustn't be null
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

    /**
     * @return error message if validation failed
     */
    String message() default "";
}
