package com.korzunva.repository.sql;

public class RoleSQL {

    public static final String NAME = "NAME";

    private static final String ROLE = "ROLE";
    private static final String USER_ROLE = "USER_ROLE";
    private static final String ID = "ID";
    private static final String ROLE_ID = "ROLE_ID";
    private static final String USER_ID = "USER_ID";

    public static final String GET_USER_ROLES =
            "SELECT " + ROLE + "." + NAME + " AS " + ROLE +
            " FROM " + USER_ROLE +
                    " JOIN " + ROLE +
                    " ON " + USER_ROLE + "." + ROLE_ID + " = " + ROLE + "." + ID +
            " WHERE " + USER_ROLE + "." + USER_ID + " = ?";

    public static final String ADD_USER_ROLE =
            "INSERT INTO " + USER_ROLE + " ( "
                + USER_ID + ", "
                + ROLE_ID + ")" +
            " VALUES(?, ?)";

}
