package com.korzunva.service.exception;

import com.korzunva.common.exception.ExceptionWithResponseCode;
import com.korzunva.model.ResponseCode;

public class ServiceException extends ExceptionWithResponseCode {

    public ServiceException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }

    public ServiceException(String message, Throwable cause, ResponseCode responseCode) {
        super(message, cause, responseCode);
    }

    public ServiceException(Throwable cause, ResponseCode responseCode) {
        super(cause, responseCode);
    }

}
