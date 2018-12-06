package com.korzunva.model;

import java.util.Arrays;

/**
 * Represents users' roles
 */
public enum Role {

    GUEST(1L, "GUEST"),
    USER(2L, "USER"),
    ADMIN(3L, "ADMIN");

    private Long id;
    private String value;

    Role(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Returns {@link Role} taken by it's string value
     * @param value role's string value
     * @return {@link Role}
     */
    public static Role getByValue(String value) {
        return Arrays.stream(Role.values())
                .filter(role -> role.value.equals(value))
                .findFirst()
                .orElse(null);
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

}
