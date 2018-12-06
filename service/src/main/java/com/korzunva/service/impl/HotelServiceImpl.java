package com.korzunva.service.impl;

import com.korzunva.common.injector.Component;
import com.korzunva.model.Hotel;
import com.korzunva.model.ResponseCode;
import com.korzunva.repository.HotelDAO;
import com.korzunva.repository.RoomDAO;
import com.korzunva.repository.exception.DAOException;
import com.korzunva.service.AbstractCrudService;
import com.korzunva.service.HotelService;
import com.korzunva.service.exception.ServiceException;

@Component
public class HotelServiceImpl extends AbstractCrudService<Hotel> implements HotelService {

    private RoomDAO roomDAO;

    public HotelServiceImpl(HotelDAO hotelDAO, RoomDAO roomDAO) {
        this.dao = hotelDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    public Hotel get(String id) throws ServiceException {
        Hotel hotel = super.get(id);

        try {
            hotel.setRooms(roomDAO.getHotelRooms(id));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e, ResponseCode.INTERNAL_SERVER_ERROR);
        }

        return hotel;
    }

}
