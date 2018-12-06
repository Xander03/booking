package com.korzunva.common.exception;

import com.korzunva.model.ResponseCode;

public class ExceptionWithResponseCode extends Exception {

    private ResponseCode responseCode;

    public ExceptionWithResponseCode(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public ExceptionWithResponseCode(String message, Throwable cause, ResponseCode responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public ExceptionWithResponseCode(Throwable cause, ResponseCode responseCode) {
        super(cause);
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

}
