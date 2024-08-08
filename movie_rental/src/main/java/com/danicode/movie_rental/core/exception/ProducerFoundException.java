package com.danicode.movie_rental.core.exception;

public class ProducerFoundException extends RuntimeException {

    public ProducerFoundException() {
        super("El productor ya existe");
    }
}
