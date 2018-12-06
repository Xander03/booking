package com.korzunva.controller.exception;

import com.korzunva.common.exception.ExceptionWithResponseCode;
import com.korzunva.model.ResponseCode;

public class ControllerException extends ExceptionWithResponseCode {

    public ControllerException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }

    public ControllerException(String message, Throwable cause, ResponseCode responseCode) {
        super(message, cause, responseCode);
    }

    public ControllerException(Throwable cause, ResponseCode responseCode) {
        super(cause, responseCode);
    }

}
