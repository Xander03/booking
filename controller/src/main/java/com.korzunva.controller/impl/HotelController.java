package com.korzunva.controller.impl;

import com.korzunva.common.injector.Component;
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
import com.korzunva.model.Hotel;
import com.korzunva.model.Role;
import com.korzunva.service.HotelService;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

@Component("hotelController")
@RestController("/api/hotels")
public class HotelController implements Controller, CrudController<Hotel> {

    private HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @Override
    @Post
    @PreAuthorized(Role.ADMIN)
    public Hotel create(@RequestBody Hotel hotel) throws ControllerException {
        try {
            return service.create(hotel);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Get
    @RequestMapping("/{id}")
    @PreAuthorized({Role.ADMIN, Role.USER})
    public Hotel get(@PathParam String id) throws ControllerException {
        try {
            return service.get(id);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Get
    @PreAuthorized({Role.ADMIN, Role.USER})
    public List<Hotel> getAll() throws ControllerException {
        try {
            return service.getAll();
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Put
    @PreAuthorized(Role.ADMIN)
    public Hotel update(@RequestBody Hotel hotel) throws ControllerException {
        try {
            return service.update(hotel);
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
