package com.korzunva.repository;

import com.korzunva.model.User;
import com.korzunva.repository.exception.DAOException;

/**
 * UserDAO provides operations to
 * work with USER table in database
 */
public interface UserDAO extends CrudDAO<User> {

    /**
     * Finds user by it's login
     * @param login user's login
     * @return found user
     * @throws DAOException if something went wrong
     */
    User getByLogin(String login) throws DAOException;

}
