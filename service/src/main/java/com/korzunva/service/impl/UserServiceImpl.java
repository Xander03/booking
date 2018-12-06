package com.korzunva.service.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.ResponseCode;
import com.korzunva.model.Role;
import com.korzunva.model.User;
import com.korzunva.repository.RoleDAO;
import com.korzunva.repository.RoomDAO;
import com.korzunva.repository.UserDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.service.AbstractCrudService;
import com.korzunva.service.UserService;
import com.korzunva.service.exception.ServiceException;

@Component
public class UserServiceImpl extends AbstractCrudService<User> implements UserService {

    private static final String USER_ALREADY_EXISTS = "service.user_exist";

    private RoleDAO roleDAO;
    private RoomDAO roomDAO;

    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, RoomDAO roomDAO) {
        this.dao = userDAO;
        this.roleDAO = roleDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    public User getByLogin(String login) throws ServiceException {
        checkParam(login);

        User user;
        try {
            user = ((UserDAO) dao).getByLogin(login);

            checkFoundEntity(user);

            user.setRoles(roleDAO.getUserRoles(user.getId()));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        return user;
    }

    @Override
    public User create(User entity) throws ServiceException {
        entity = super.create(entity);

        try {
            entity.getRoles().remove(Role.GUEST);
            entity.getRoles().add(roleDAO.addUserRole(entity.getId(), Role.USER));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        return entity;
    }

    @Override
    public User get(String id) throws ServiceException {
        User user = super.get(id);

        try {
            user.setRoles(roleDAO.getUserRoles(id));
            user.setRooms(roomDAO.getUserRooms(id));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        return user;
    }

}
