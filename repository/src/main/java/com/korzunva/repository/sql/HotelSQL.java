package com.korzunva.repository.sql;

public class HotelSQL {

    private static final String HOTEL = "HOTEL";
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String ADDRESS = "ADDRESS";

    public static final String CREATE =
            "INSERT INTO " + HOTEL + "( " +
                    ID + ", " +
                    NAME + ", " +
                    ADDRESS + ")" +
                    " VALUES(?, ?, ?)";

    public static final String GET =
            "SELECT * FROM " + HOTEL +
                    " WHERE " + ID + " = ?";

    public static final String GET_ALL =
            "SELECT * FROM " + HOTEL;

    public static final String UPDATE =
            "UPDATE " + HOTEL + " SET " +
                    NAME + " = ?, " +
                    ADDRESS + " = ? " +
                    "WHERE " + ID + " = ?";

    public static final String DELETE =
            "DELETE FROM " + HOTEL +
                    " WHERE " + ID + " = ?";

}
