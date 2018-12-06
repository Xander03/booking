package com.korzunva.service;

import com.korzunva.model.Role;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

/**
 * Provides operations to work
 * with {@link Role}
 */
public interface RoleService {

    /**
     * Returns roles found by user's id
     * @param userId user's id
     * @return user's roles
     * @throws ServiceException when invalid param received
     * or exception was thrown by lower layer
     */
    List<Role> getUserRoles(String userId) throws ServiceException;

    /**
     * Adds new role to user
     * @param userId user's id
     * @param role new user's role
     * @return added role
     * @throws ServiceException when invalid param received
     * or exception was thrown by lower layer
     */
    Role addUserRole(String userId, Role role) throws ServiceException;

}
