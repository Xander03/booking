package com.korzunva.repository.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.Hotel;
import com.korzunva.model.Room;
import com.korzunva.model.User;
import com.korzunva.repository.AbstractCrudDAO;
import com.korzunva.repository.RoomDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.repository.sql.RoomSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDAOImpl extends AbstractCrudDAO<Room> implements RoomDAO {

    @Override
    public List<Room> getUserRooms(String userId) throws DAOException {
        return getBySomeId(userId, RoomSQL.GET_BY_USER_ID);
    }

    @Override
    public List<Room> getFree(String userId) throws DAOException {
        return getBySomeId(userId, RoomSQL.GET_ALL_FREE);
    }

    @Override
    public List<Room> getHotelRooms(String hotelId) throws DAOException {
        return getBySomeId(hotelId, RoomSQL.GET_BY_HOTEL_ID);
    }

    @Override
    protected PreparedStatement prepareCreateStatement(Room entity) throws DAOException {
        PreparedStatement statement = getPreparedStatement(RoomSQL.CREATE);
        try {
            statement.setString(1, entity.getId());
            statement.setInt(2, entity.getFloor());
            statement.setInt(3, entity.getPlaces());
            statement.setString(4, entity.getHotel().getId());
            setNullableValue(5, statement, entity.getUser());
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareGetStatement(String id) throws DAOException {
        PreparedStatement statement = getPreparedStatement(RoomSQL.GET);
        try {
            statement.setString(1, id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareGetAllStatement() throws DAOException {
        return getPreparedStatement(RoomSQL.GET_ALL);
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Room entity) throws DAOException {
        PreparedStatement statement = getPreparedStatement(RoomSQL.UPDATE);
        try {
            statement.setInt(1, entity.getFloor());
            statement.setInt(2, entity.getPlaces());
            statement.setString(3, entity.getHotel().getId());
            setNullableValue(4, statement, entity.getUser());
            statement.setString(5, entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareDeleteStatement(String id) throws DAOException {
        PreparedStatement statement = getPreparedStatement(RoomSQL.DELETE);
        try {
            statement.setString(1, id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected Room fromRow(ResultSet rs) throws DAOException {
        try {
            String id = rs.getString(RoomSQL.ID);
            int floor = rs.getInt(RoomSQL.FLOOR);
            int places = rs.getInt(RoomSQL.PLACES);
            String hotelId = rs.getString(RoomSQL.HOTEL_ID);
            String userId = rs.getString(RoomSQL.USER_ID);

            Hotel hotel = null;
            if (hotelId != null && !hotelId.equals("")) {
                hotel = new Hotel(hotelId);
            }

            User user = null;
            if (userId != null && !userId.equals("")) {
                user = new User(userId);
            }

            return new Room(id, floor, places, hotel, user);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    private List<Room> getBySomeId(String id, String sql) throws DAOException {
        ResultSet rs = null;
        try (PreparedStatement statement = getPreparedStatement(sql)) {
            statement.setString(1, id);
            rs = statement.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                rooms.add(fromRow(rs));
            }
            return rooms;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
        }
    }

}
