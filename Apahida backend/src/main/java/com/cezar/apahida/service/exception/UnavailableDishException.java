package com.cezar.apahida.service.exception;

public class UnavailableDishException extends Exception {
    public UnavailableDishException(){

    }
    public UnavailableDishException(String message) {
        super(message);
    }
}
