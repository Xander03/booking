package com.korzunva.controller.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.controller.CrudController;
import com.korzunva.controller.exception.ControllerException;
import com.korzunva.dispatcher.Controller;
import com.korzunva.dispatcher.annotation.Delete;
import com.korzunva.dispatcher.annotation.Get;
import com.korzunva.dispatcher.annotation.Patch;
import com.korzunva.dispatcher.annotation.PathParam;
import com.korzunva.dispatcher.annotation.Post;
import com.korzunva.dispatcher.annotation.PreAuthorized;
import com.korzunva.dispatcher.annotation.Put;
import com.korzunva.dispatcher.annotation.RequestBody;
import com.korzunva.dispatcher.annotation.RequestMapping;
import com.korzunva.dispatcher.annotation.RestController;
import com.korzunva.model.Role;
import com.korzunva.model.Room;
import com.korzunva.service.RoomService;
import com.korzunva.service.exception.ServiceException;

import java.util.List;

@Component("roomController")
@RestController("/api/rooms")
public class RoomController implements Controller, CrudController<Room> {

    private RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @Get
    @RequestMapping("/reserved/{userId}")
    @PreAuthorized({Role.ADMIN, Role.USER})
    public List<Room> getReservedRooms(@PathParam String userId) throws ControllerException {
        try {
            return service.getByUserId(userId);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Get
    @RequestMapping("/free/{userId}")
    @PreAuthorized({Role.ADMIN, Role.USER})
    public List<Room> getFreeRooms(@PathParam String userId) throws ControllerException {
        try {
            return service.getFree(userId);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Patch
    @RequestMapping("/{id}/unreserve")
    @PreAuthorized({Role.ADMIN, Role.USER})
    public void cancelReservation(@PathParam String id) throws ControllerException {
        try {
            service.cancelReservation(id);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Patch
    @RequestMapping("/{roomId}/reserve/{userId}")
    @PreAuthorized({Role.ADMIN, Role.USER})
    public void reserve(@PathParam String roomId, @PathParam String userId) throws ControllerException {
        try {
            service.reserve(roomId, userId);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Patch
    @RequestMapping("/{roomId}/hotel/{hotelId}")
    @PreAuthorized(Role.ADMIN)
    public void updateHotel(@PathParam String roomId, @PathParam String hotelId) throws ControllerException {
        try {
            service.updateHotel(roomId, hotelId);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Post
    @PreAuthorized(Role.ADMIN)
    public Room create(@RequestBody Room room) throws ControllerException {
        try {
            return service.create(room);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Get
    @RequestMapping("/{id}")
    @PreAuthorized({Role.ADMIN, Role.USER})
    public Room get(@PathParam String id) throws ControllerException {
        try {
            return service.get(id);
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Get
    @PreAuthorized({Role.ADMIN, Role.USER})
    public List<Room> getAll() throws ControllerException {
        try {
            return service.getAll();
        } catch (ServiceException e) {
            throw new ControllerException(e.getMessage(), e, e.getResponseCode());
        }
    }

    @Override
    @Put
    @PreAuthorized(Role.ADMIN)
    public Room update(@RequestBody Room room) throws ControllerException {
        try {
            return service.update(room);
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
