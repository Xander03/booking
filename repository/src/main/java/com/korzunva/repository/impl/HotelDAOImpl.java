package com.korzunva.repository.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.Hotel;
import com.korzunva.repository.AbstractCrudDAO;
import com.korzunva.repository.HotelDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.repository.sql.HotelSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HotelDAOImpl extends AbstractCrudDAO<Hotel> implements HotelDAO {

    @Override
    protected PreparedStatement prepareCreateStatement(Hotel entity) throws DAOException {
        PreparedStatement statement = getPreparedStatement(HotelSQL.CREATE);
        try {
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getAddress());
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PREPARE_CREATE_EXCEPTION), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareGetStatement(String id) throws DAOException {
        PreparedStatement statement = getPreparedStatement(HotelSQL.GET);
        try {
            statement.setString(1, id);
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PREPARE_GET_EXCEPTION), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareGetAllStatement() throws DAOException {
        return getPreparedStatement(HotelSQL.GET_ALL);
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(Hotel entity) throws DAOException {
        PreparedStatement statement = getPreparedStatement(HotelSQL.UPDATE);
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAddress());
            statement.setString(3, entity.getId());
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PREPARE_UPDATE_EXCEPTION), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareDeleteStatement(String id) throws DAOException {
        PreparedStatement statement = getPreparedStatement(HotelSQL.DELETE);
        try {
            statement.setString(1, id);
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PREPARE_DELETE_EXCEPTION), e);
        }
        return statement;
    }

    @Override
    protected Hotel fromRow(ResultSet rs) throws DAOException {
        try {
            String id = rs.getString(HotelSQL.ID);
            String name = rs.getString(HotelSQL.NAME);
            String address = rs.getString(HotelSQL.ADDRESS);

            return new Hotel(id, name, address);
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PARSE_RESULT_SET_EXCEPTION), e);
        }
    }

}
