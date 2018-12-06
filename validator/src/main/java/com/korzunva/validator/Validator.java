package com.korzunva.validator;

/**
 * Validator interface
 * @param <FieldType> Class of field value
 * @param <AnnotationType> Class of validation annotation
 */
public interface Validator<FieldType, AnnotationType> {


    /**
     * Validates field value
     * @param field field value
     * @param annotation validation annotation
     * @return error message if validation failed or null
     */
    String validate(FieldType field, AnnotationType annotation);

}
