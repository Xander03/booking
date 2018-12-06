package com.korzunva.service;

import com.korzunva.common.messages.MessageProperties;
import com.korzunva.model.ResponseCode;
import com.korzunva.service.exception.ServiceException;

public abstract class AbstractService {

    protected static final MessageProperties messages = MessageProperties.INSTANCE;

    private static final String INVALID_PARAM_EXCEPTION = "service.invalid_param";

    protected void checkParam(String param) throws ServiceException {
        if (param == null || param.equals("")) {
            throw new ServiceException(messages.getProperty(INVALID_PARAM_EXCEPTION), ResponseCode.BAD_REQUEST);
        }
    }

    protected void checkParam(Object param) throws ServiceException {
        if (param == null) {
            throw new ServiceException(messages.getProperty(INVALID_PARAM_EXCEPTION), ResponseCode.BAD_REQUEST);
        }
    }

}
