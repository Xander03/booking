package com.korzunva.dispatcher.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Indicates that parameter value must be taken from query part of the uri*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface QueryParam {
}
