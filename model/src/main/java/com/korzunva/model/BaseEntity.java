package com.korzunva.model;

import com.korzunva.validator.Validatable;

import java.io.Serializable;

/**
 * Represents entity that can be stored in database
 */
public class BaseEntity implements Serializable, Validatable {

    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
