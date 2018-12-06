package com.korzunva.dispatcher.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Indicates that method will process HTTP method POST*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Post {
}
