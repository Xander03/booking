package com.korzunva.repository.sql;

public class RoomSQL {

    private static final String ROOM = "ROOM";
    public static final String ID = "ID";
    public static final String FLOOR = "FLOOR";
    public static final String PLACES = "PLACES";
    public static final String HOTEL_ID = "HOTEL_ID";
    public static final String USER_ID = "USER_ID";

    public static final String CREATE =
            "INSERT INTO " + ROOM + "( " +
                    ID + ", " +
                    FLOOR + ", " +
                    PLACES + ", " +
                    HOTEL_ID + ", " +
                    USER_ID + ")" +
            " VALUES(?, ?, ?, ?, ?)";

    public static final String GET =
            "SELECT * FROM " + ROOM +
            " WHERE " + ID + " = ?";

    public static final String GET_ALL =
            "SELECT * FROM " + ROOM;

    public static final String GET_ALL_FREE =
            "SELECT * FROM " + ROOM +
            " WHERE " + USER_ID + " IS NULL " +
            " OR " + USER_ID + " = ?";

    public static final String GET_BY_USER_ID =
            "SELECT * FROM " + ROOM +
            " WHERE " + USER_ID + " = ?";

    public static final String GET_BY_HOTEL_ID =
            "SELECT * FROM " + ROOM +
            " WHERE " + HOTEL_ID + " = ?";

    public static final String UPDATE =
            "UPDATE " + ROOM + " SET " +
                    FLOOR + " = ?, " +
                    PLACES + " = ?, " +
                    HOTEL_ID + " = ?, " +
                    USER_ID + " = ? " +
            "WHERE " + ID + " = ?";

    public static final String DELETE =
            "DELETE FROM " + ROOM +
            " WHERE " + ID + " = ?";

}
