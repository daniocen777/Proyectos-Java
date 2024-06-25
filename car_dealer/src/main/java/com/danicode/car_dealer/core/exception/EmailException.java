package com.danicode.car_dealer.core.exception;

public class EmailException extends RuntimeException {
    public EmailException() {
        super("Wrong format email");
    }
}
