package com.korzunva.repository;

import com.korzunva.model.BaseEntity;
import com.korzunva.repository.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCrudDAO<Entity extends BaseEntity>
        extends AbstractDAO<Entity> implements CrudDAO<Entity> {

    private static final String CRETE_EXCEPTION = "dao.create";
    private static final String GET_EXCEPTION = "dao.get";
    private static final String GET_ALL_EXCEPTION = "dao.get_all";
    private static final String UPDATE_EXCEPTION = "dao.update";
    private static final String DELETE_EXCEPTION = "dao.delete";

    protected static final String PREPARE_CREATE_EXCEPTION = "dao.prepare_create";
    protected static final String PREPARE_GET_EXCEPTION = "dao.prepare_get";
    protected static final String PREPARE_UPDATE_EXCEPTION = "dao.prepare_update";
    protected static final String PREPARE_DELETE_EXCEPTION = "dao.prepare_delete";
    protected static final String PARSE_RESULT_SET_EXCEPTION = "dao.parse_result_set";

    protected abstract PreparedStatement prepareCreateStatement(Entity entity) throws DAOException;
    protected abstract PreparedStatement prepareGetStatement(String id) throws DAOException;
    protected abstract PreparedStatement prepareGetAllStatement() throws DAOException;
    protected abstract PreparedStatement prepareUpdateStatement(Entity entity) throws DAOException;
    protected abstract PreparedStatement prepareDeleteStatement(String id) throws DAOException;

    @Override
    public Entity create(Entity entity) throws DAOException {
        entity.setId(UUID.randomUUID().toString());
        try (PreparedStatement statement = prepareCreateStatement(entity)) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(CRETE_EXCEPTION), e);
        }
        return entity;
    }

    @Override
    public Entity get(String id) throws DAOException {
        try (PreparedStatement statement = prepareGetStatement(id);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                return fromRow(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(GET_EXCEPTION), e);
        }
    }

    @Override
    public List<Entity> getAll() throws DAOException {
        List<Entity> entities = new ArrayList<>();
        try (PreparedStatement statement = prepareGetAllStatement();
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                entities.add(fromRow(rs));
            }
            return entities;

        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(GET_ALL_EXCEPTION), e);
        }
    }

    @Override
    public Entity update(Entity entity) throws DAOException {
        try (PreparedStatement statement = prepareUpdateStatement(entity)) {

            statement.executeUpdate();
            return entity;

        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(UPDATE_EXCEPTION), e);
        }
    }

    @Override
    public void delete(String id) throws DAOException {
        try (PreparedStatement statement = prepareDeleteStatement(id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(messages.getProperty(DELETE_EXCEPTION), e);
        }
    }

}
