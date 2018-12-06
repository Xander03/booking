package com.korzunva.repository;

import com.korzunva.model.BaseEntity;
import com.korzunva.repository.exception.DAOException;

import java.util.List;

/**
 * CrudDAO provides simple CRUD operations
 */
public interface CrudDAO<Entity extends BaseEntity> {

    /**
     * Creates new entity
     * @param entity entity that will be created
     * @return created entity
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    Entity create(Entity entity) throws DAOException;

    /**
     * Returns existing entity
     * @param id entity's id
     * @return found entity
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    Entity get(String id) throws DAOException;

    /**
     * @return all existing entities
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    List<Entity> getAll() throws DAOException;

    /**
     * Updates entity
     * @param entity entity will be updated
     * @return updated entity
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    Entity update(Entity entity) throws DAOException;

    /**
     * Deletes entity
     * @param id entity's id
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    void delete(String id) throws DAOException;

}
