package com.korzunva.repository.sql;

public class UserSQL {

    private static final String USER = "USER";
    public static final String ID = "ID";
    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";

    public static final String CREATE =
            "INSERT INTO " + USER + "( " +
                    ID + ", " +
                    LOGIN + ", " +
                    PASSWORD + ")" +
                    " VALUES(?, ?, ?)";

    public static final String GET =
            "SELECT * FROM " + USER +
                    " WHERE " + ID + " = ?";

    public static final String GET_BY_LOGIN =
            "SELECT * FROM " + USER +
                    " WHERE " + LOGIN + " = ?";

    public static final String GET_ALL =
            "SELECT * FROM " + USER;

    public static final String UPDATE =
            "UPDATE " + USER + " SET " +
                    LOGIN + " = ?, " +
                    PASSWORD + " = ? " +
                    "WHERE " + ID + " = ?";

    public static final String DELETE =
            "DELETE FROM " + USER +
                    " WHERE " + ID + " = ?";

}
