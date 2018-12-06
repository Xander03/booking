package com.korzunva.service;

import com.korzunva.model.BaseEntity;
import com.korzunva.model.ResponseCode;
import com.korzunva.repository.CrudDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.service.exception.ServiceException;
import com.korzunva.validator.EntityValidator;

import java.util.List;

public class AbstractCrudService<Entity extends BaseEntity>
        extends AbstractService implements CrudService<Entity> {

    private static final String RECEIVED_NULL_ENTITY = "service.received_null";
    private static final String FOUND_NULL_ENTITY = "service.found_null";

    protected CrudDAO<Entity> dao;

    @Override
    public Entity create(Entity entity) throws ServiceException {
        checkReceivedEntity(entity);

        try {
            entity = dao.create(entity);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        checkFoundEntity(entity);

        return entity;
    }

    @Override
    public Entity get(String id) throws ServiceException {
        checkParam(id);

        Entity entity;
        try {
            entity = dao.get(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        checkFoundEntity(entity);

        return entity;
    }

    @Override
    public List<Entity> getAll() throws ServiceException {
        try {
            return dao.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Entity update(Entity entity) throws ServiceException {
        checkReceivedEntity(entity);

        try {
            entity = dao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        checkFoundEntity(entity);

        return entity;
    }

    @Override
    public void delete(String id) throws ServiceException {
        checkParam(id);

        try {
            dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void checkReceivedEntity(Entity entity) throws ServiceException {
        if (entity == null) {
            throw new ServiceException(messages.getProperty(RECEIVED_NULL_ENTITY), ResponseCode.BAD_REQUEST);
        }

        List<String> errors = EntityValidator.validate(entity);
        if (errors.size() > 0) {
            throw new ServiceException(errors.toString(), ResponseCode.BAD_REQUEST);
        }
    }

    protected void checkFoundEntity(Entity entity) throws ServiceException {
        if (entity ==  null) {
            throw new ServiceException(messages.getProperty(FOUND_NULL_ENTITY), ResponseCode.NOT_FOUND);
        }

        List<String> errors = EntityValidator.validate(entity);
        if (errors.size() > 0) {
            throw new ServiceException(errors.toString(), ResponseCode.NOT_FOUND);
        }
    }

}
