package com.korzunva.repository.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.common.utils.Base64Encoder;
import com.korzunva.model.User;
import com.korzunva.repository.AbstractCrudDAO;
import com.korzunva.repository.UserDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.repository.sql.UserSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserDAOImpl extends AbstractCrudDAO<User> implements UserDAO {

    @Override
    public User getByLogin(String login) throws DAOException {
        ResultSet rs = null;
        PreparedStatement statement = getPreparedStatement(UserSQL.GET_BY_LOGIN);
        try {
            statement.setString(1, login);

            rs = statement.executeQuery();
            if (rs.next()) {
                return fromRow(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        } finally {
            closeResultSet(rs);
        }
    }

    @Override
    protected PreparedStatement prepareCreateStatement(User entity) throws DAOException {
        PreparedStatement statement = getPreparedStatement(UserSQL.CREATE);
        try {
            statement.setString(1, entity.getId());
            statement.setString(2, entity.getLogin());
            statement.setString(3, entity.getPassword());
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareGetStatement(String id) throws DAOException {
        PreparedStatement statement = getPreparedStatement(UserSQL.GET);
        try {
            statement.setString(1, id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareGetAllStatement() throws DAOException {
        return getPreparedStatement(UserSQL.GET_ALL);
    }

    @Override
    protected PreparedStatement prepareUpdateStatement(User entity) throws DAOException {
        PreparedStatement statement = getPreparedStatement(UserSQL.UPDATE);
        try {
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getId());
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected PreparedStatement prepareDeleteStatement(String id) throws DAOException {
        PreparedStatement statement = getPreparedStatement(UserSQL.DELETE);
        try {
            statement.setString(1, id);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return statement;
    }

    @Override
    protected User fromRow(ResultSet rs) throws DAOException {
        try {
            String id = rs.getString(UserSQL.ID);
            String login = rs.getString(UserSQL.LOGIN);
            String password = rs.getString(UserSQL.PASSWORD);

            return new User(id, login, password);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
