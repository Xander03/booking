package com.korzunva.repository;

import com.korzunva.model.Room;
import com.korzunva.repository.exception.DAOException;

import java.util.List;

/**
 * RoomDAO provides operations to
 * work with ROOM table in database
 */
public interface RoomDAO extends CrudDAO<Room> {

    /**
     * Returns rooms that reserved by user
     * @param userId user's id
     * @return rooms reserved by user
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    List<Room> getUserRooms(String userId) throws DAOException;

    /**
     * @param userId current user's id
     * @return free rooms
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    List<Room> getFree(String userId) throws DAOException;

    /**
     * Returns rooms that located in the hotel
     * @param hotelId hotel's id
     * @return rooms that located in the hotel
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    List<Room> getHotelRooms(String hotelId) throws DAOException;

}
