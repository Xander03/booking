package com.korzunva.validator.impl;

import com.korzunva.validator.Validator;
import com.korzunva.validator.annotations.NotNull;

public class NotNullValidatorImpl implements Validator<Object, NotNull> {

    private static final String DEFAULT_MESSAGE = "Value of field '%s' mustn't be null";

    @Override
    public String validate(Object field, NotNull annotation) {
        if (field == null) {
            return !annotation.message().equals("") ? annotation.message() : DEFAULT_MESSAGE;
        }
        return null;
    }
}
