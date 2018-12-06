package com.korzunva.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation mark field which value must be in
 * range from {@link  Range#min()} to {@link Range#max()}
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    /**
     * @return min value of the field
     */
    double min() default 0.0;

    /**
     * @return max value of the field
     */
    double max() default Double.MAX_VALUE;

    /**
     * @return error message if validation failed
     */
    String message() default "";
}
