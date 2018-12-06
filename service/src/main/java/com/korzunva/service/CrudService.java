package com.korzunva.service;

import com.korzunva.model.BaseEntity;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

/**
 * CrudService provides CRUD operations
 * to work with Entities
 */
public interface CrudService<Entity extends BaseEntity> {

    /**
     * Creates new Entity
     * @param entity entity that will be created
     * @return created entity
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    Entity create(Entity entity) throws ServiceException;

    /**
     * Returns existing entity found by it's id
     * @param id entity's id
     * @return existing entity
     * @throws ServiceException when invalid param received
     * or exception was thrown by lower layer
     */
    Entity get(String id) throws ServiceException;

    /**
     * @return all existing entities
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    List<Entity> getAll() throws ServiceException;

    /**
     * Updates existing entity
     * @param entity entity that will be updated
     * @return updated entity
     * @throws ServiceException when invalid entity received
     * or exception was thrown by lower layer
     */
    Entity update(Entity entity) throws ServiceException;

    /**
     * Deletes entity by it's id
     * @param id entity's id
     * @throws ServiceException when invalid param received
     * or exception was thrown by lower layer
     */
    void delete(String id) throws ServiceException;

}
