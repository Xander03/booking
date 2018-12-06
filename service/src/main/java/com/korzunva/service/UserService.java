package com.korzunva.service;

import com.korzunva.model.User;
import com.korzunva.service.exception.ServiceException;

/**
 * UserServiceImpl provides CRUD operations to
 * {@link User} entity and performs validation
 */
public interface UserService extends CrudService<User> {

    /**
     * Finds user by it's login
     * @param login user's login
     * @return found user
     * @throws ServiceException if something went wrong
     */
    User getByLogin(String login) throws ServiceException;

}
