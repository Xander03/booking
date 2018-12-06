package com.korzunva.controller.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.common.messages.MessageProperties;
import com.korzunva.common.utils.Base64Encoder;
import com.korzunva.controller.CrudController;
import com.korzunva.controller.exception.ControllerException;
import com.korzunva.dispatcher.Controller;
import com.korzunva.dispatcher.annotation.Delete;
import com.korzunva.dispatcher.annotation.Get;
import com.korzunva.dispatcher.annotation.PathParam;
import com.korzunva.dispatcher.annotation.Post;
import com.korzunva.dispatcher.annotation.PreAuthorized;
import com.korzunva.dispatcher.annotation.Put;
import com.korzunva.dispatcher.annotation.RequestBody;
import com.korzunva.dispatcher.annotation.RequestMapping;
import com.korzunva.dispatcher.annotation.RestController;
import com.korzunva.dispatcher.utils.JsonParser;
import com.korzunva.model.ResponseCode;
import com.korzunva.model.Role;
import com.korzunva.model.User;
import com.korzunva.service.UserService;
import com.korzunva.service.exception.ServiceException;

import java.io.IOException;
import java.util.List;

@Component("userController")
@RestController("/api/users")
public class UserController implements Controller, CrudController<User> {

    private static final String INVALID_PASSWORD = "controller.invalid_password";

    private UserService service;
    private MessageProperties messages;

    public UserController(UserService userService) {
        this.service = userService;
        this.messages = MessageProperties.INSTANCE;
    }

    @Post
    @RequestMapping("/signIn")
    @PreAuthorized(Role.GUEST)
    public String signIn(@RequestBody User user) throws ControllerException {
        try {
            User existingUser = service.getByLogin(user.getLogin());
            if (existingUser.getPassword().equals(user.getPassword())) {
                String userJson = JsonParser.objectToJson(existingUser);
                return Base64Encoder.encode(userJson);
            }
            throw new ControllerException(messages.getProperty(INVALID_PASSWORD), ResponseCode.BAD_REQUEST);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        } catch (IOException e) {
            throw new ControllerException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Post
    @PreAuthorized(Role.GUEST)
    public User create(@RequestBody User user) throws ControllerException {
        try {
            return service.create(user);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Get
    @RequestMapping("/{id}")
    @PreAuthorized(Role.ADMIN)
    public User get(@PathParam String id) throws ControllerException {
        try {
            return service.get(id);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Get
    @PreAuthorized(Role.ADMIN)
    public List<User> getAll() throws ControllerException {
        try {
            return service.getAll();
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Put
    @PreAuthorized(Role.ADMIN)
    public User update(@RequestBody User user) throws ControllerException {
        try {
            return service.update(user);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Delete
    @RequestMapping("/{id}")
    @PreAuthorized(Role.ADMIN)
    public void delete(@PathParam String id) throws ControllerException {
        try {
            service.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }
}
