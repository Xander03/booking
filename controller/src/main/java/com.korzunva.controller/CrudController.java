package com.korzunva.controller;


import com.korzunva.controller.exception.ControllerException;
import com.korzunva.dispatcher.annotation.Post;
import com.korzunva.dispatcher.annotation.RequestBody;

import java.util.List;

/**
 * CrudController contains simple CRUD operations,
 * that should be implemented
 * @param <Entity> entity to work with
 */
public interface CrudController<Entity> {

    /**
     * Creates new entity
     * @param entity entity to be created
     * @return created entity
     * @throws ControllerException if something went wrong
     * or lower layer throw exception
     */
    Entity create(Entity entity) throws ControllerException;

    /**
     * Returns existing entity
     * @param id entity's id
     * @return found entity
     * @throws ControllerException if something went wrong
     * or lower layer throw exception
     */
    Entity get(String id) throws ControllerException;

    /**
     * @return list of existing entities
     * @throws ControllerException if something went wrong
     * or lower layer throw exception
     */
    List<Entity> getAll() throws ControllerException;

    /**
     * Updated existing entity
     * @param entity entity that will be updated
     * @return updated entity
     * @throws ControllerException if something went wrong
     * or lower layer throw exception
     */
    Entity update(Entity entity) throws ControllerException;

    /**
     * Deletes existing entity
     * @param id entity's id;
     * @throws ControllerException if something went wrong
     * or lower layer throw exception
     */
    void delete(String id) throws ControllerException;

}
