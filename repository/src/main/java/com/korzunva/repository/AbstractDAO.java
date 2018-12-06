package com.korzunva.repository;

import com.korzunva.common.messages.MessageProperties;
import com.korzunva.database.ConnectionPool;
import com.korzunva.model.BaseEntity;
import com.korzunva.repository.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public abstract class AbstractDAO<Entity> {

    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    protected static final MessageProperties messages = MessageProperties.INSTANCE;

    private static final String PREPARE_SQL_EXCEPTION = "dao.prepare_sql";
    private static final String CLOSE_RESULT_SET_EXCEPTION = "dao.close_result_set";

    protected abstract Entity fromRow(ResultSet rs) throws DAOException;

    protected PreparedStatement getPreparedStatement(String sql) throws DAOException {
        Connection connection = connectionPool.getConnection();
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PREPARE_SQL_EXCEPTION), e);
        }
    }

    protected <T extends BaseEntity> void setNullableValue(
            int paramIndex, PreparedStatement preparedStatement, T entity) throws DAOException {

        try {
            if (entity == null) {
                preparedStatement.setNull(paramIndex, Types.NULL);
            } else {
                preparedStatement.setString(paramIndex, entity.getId());
            }
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(PREPARE_SQL_EXCEPTION), e);
        }
    }

    protected void closeResultSet(ResultSet rs) throws DAOException {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(CLOSE_RESULT_SET_EXCEPTION), e);
        }
    }

}
