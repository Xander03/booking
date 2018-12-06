package com.korzunva.service;

import com.korzunva.model.Room;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

/**
 * RoomService provides CRUD operations to
 * {@link Room} entity and performs validation
 */
public interface RoomService extends CrudService<Room> {

    /**
     * Returns list of rooms that {@link com.korzunva.model.User} reserved
     * @param userId user's id
     * @return list of rooms reserved by user
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    List<Room> getByUserId(String userId) throws ServiceException;

    /**
     * Returns list of rooms that are free
     * @param userId current user's id
     * @return free rooms
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    List<Room> getFree(String userId) throws ServiceException;

    /**
     * @param hotelId hotel's id
     * @return list of rooms that located in {@link com.korzunva.model.Hotel}
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    List<Room> getByHotelId(String hotelId) throws ServiceException;

    /**
     * Cancels reservation of room
     * @param id room's id
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    void cancelReservation(String id) throws ServiceException;

    /**
     * Reserves room
     * @param roomId room's id
     * @param userId user's id
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    void reserve(String roomId, String userId) throws ServiceException;


    /**
     * Updated room's hotel
     * @param roomId room's id
     * @param hotelId hotel's id
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    void updateHotel(String roomId, String hotelId) throws ServiceException;

}
