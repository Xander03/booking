package com.korzunva.repository;

import com.korzunva.model.Role;
import com.korzunva.repository.exception.DAOException;

import java.util.List;

/**
 * RoleDAO provides operation to
 * work with ROLE table in database
 */
public interface RoleDAO {

    /**
     * Returns user's roles
     * @param userId user's id
     * @return user's roles
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    List<Role> getUserRoles(String userId) throws DAOException;

    /**
     * Adds new {@link Role} to existing {@link com.korzunva.model.User}
     * @param userId user's id
     * @param role new user's role
     * @return added role
     * @throws DAOException when occurs {@link java.sql.SQLException}
     * or something went wrong
     */
    Role addUserRole(String userId, Role role) throws DAOException;

}
