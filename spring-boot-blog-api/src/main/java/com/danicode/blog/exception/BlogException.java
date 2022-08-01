package com.danicode.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException {
    // private static final long SERIAL_VERSION_ID = 1L;
    private HttpStatus estado;
    private String mensaje;

    public BlogException() {
    }

    public BlogException(HttpStatus estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
