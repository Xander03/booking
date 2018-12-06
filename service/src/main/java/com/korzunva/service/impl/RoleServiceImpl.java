package com.korzunva.service.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.ResponseCode;
import com.korzunva.model.Role;
import com.korzunva.repository.RoleDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.service.AbstractService;
import com.korzunva.service.RoleService;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

@Component
public class RoleServiceImpl extends AbstractService implements RoleService {

    private RoleDAO dao;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.dao = roleDAO;
    }

    @Override
    public List<Role> getUserRoles(String userId) throws ServiceException {
        checkParam(userId);

        try {
            return dao.getUserRoles(userId);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Role addUserRole(String userId, Role role) throws ServiceException {
        checkParam(userId);
        checkParam(role);

        try {
            return dao.addUserRole(userId, role);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

}
