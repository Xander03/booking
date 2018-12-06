package com.korzunva.validator.impl;

import com.korzunva.validator.Validator;
import com.korzunva.validator.annotations.Length;

public class LengthValidatorImpl implements Validator<String, Length> {

    private static final String DEFAULT_MESSAGE = "Length field's '%s' value must be grater than ";

    @Override
    public String validate(String field, Length annotation) {
        if (field.length() < annotation.length()) {
            return !annotation.message().equals("")
                    ? annotation.message()
                    : DEFAULT_MESSAGE + annotation.length();
        }
        return null;
    }

}
