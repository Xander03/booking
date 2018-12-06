package com.korzunva.service.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.ResponseCode;
import com.korzunva.model.Room;
import com.korzunva.repository.HotelDAO;
import com.korzunva.repository.RoomDAO;
import com.korzunva.repository.UserDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.service.AbstractCrudService;
import com.korzunva.service.RoomService;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

@Component
public class RoomServiceImpl extends AbstractCrudService<Room> implements RoomService {

    private HotelDAO hotelDAO;
    private UserDAO userDAO;

    public RoomServiceImpl(RoomDAO roomDAO, HotelDAO hotelDAO, UserDAO userDAO) {
        this.dao = roomDAO;
        this.hotelDAO = hotelDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Room get(String id) throws ServiceException {
        Room room = super.get(id);

        try {
            room.setHotel(hotelDAO.get(room.getHotel().getId()));
            if (room.getUser() != null) {
                room.setUser(userDAO.get(room.getUser().getId()));
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        return room;
    }

    @Override
    public List<Room> getByUserId(String userId) throws ServiceException {
        checkParam(userId);

        try {
            List<Room> rooms = ((RoomDAO) dao).getUserRooms(userId);
            for (Room room : rooms) {
                room.setHotel(hotelDAO.get(room.getHotel().getId()));
            }
            return rooms;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<Room> getFree(String userId) throws ServiceException {
        checkParam(userId);

        try {
            List<Room> rooms = ((RoomDAO) dao).getFree(userId);
            for (Room room : rooms) {
                room.setHotel(hotelDAO.get(room.getHotel().getId()));
            }
            return rooms;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Room> getByHotelId(String hotelId) throws ServiceException {
        checkParam(hotelId);

        try {
            List<Room> rooms = ((RoomDAO) dao).getHotelRooms(hotelId);
            for (Room room : rooms) {
                if (room.getUser() != null) {
                    room.setUser(userDAO.get(room.getUser().getId()));
                }
            }
            return rooms;
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void cancelReservation(String id) throws ServiceException {
        checkParam(id);

        try {
            Room room = dao.get(id);

            checkFoundEntity(room);
            room.setUser(null);

            dao.update(room);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void reserve(String roomId, String userId) throws ServiceException {
        checkParam(roomId);
        checkParam(userId);

        try {
            Room room = dao.get(roomId);

            checkFoundEntity(room);
            room.setUser(userDAO.get(userId));

            dao.update(room);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateHotel(String roomId, String hotelId) throws ServiceException {
        checkParam(roomId);
        checkParam(hotelId);

        try {
            Room room = dao.get(roomId);

            checkFoundEntity(room);
            room.setHotel(hotelDAO.get(hotelId));

            dao.update(room);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Room> getAll() throws ServiceException {
        List<Room> rooms = super.getAll();

        try {
            for (Room room : rooms) {
                room.setHotel(hotelDAO.get(room.getHotel().getId()));
            }
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        return rooms;
    }

}
