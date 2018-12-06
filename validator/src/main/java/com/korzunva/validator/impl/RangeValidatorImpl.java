package com.korzunva.validator.impl;

import com.korzunva.validator.Validator;
import com.korzunva.validator.annotations.Range;

public class RangeValidatorImpl implements Validator<Number, Range> {

    private static final String DEFAULT_MESSAGE = "Value of field '%s' must be in range ";

    @Override
    public String validate(Number field, Range annotation) {
        double doubleValue = field.doubleValue();
        if (doubleValue < annotation.min() || doubleValue > annotation.max()) {
            return !annotation.message().equals("")
                    ? annotation.message()
                    : DEFAULT_MESSAGE + annotation.min() + " - " + annotation.max();
        }
        return null;
    }

}
