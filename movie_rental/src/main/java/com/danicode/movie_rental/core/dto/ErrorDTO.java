package com.danicode.movie_rental.core.dto;

public class ErrorDTO {
    private String filed;
    private String message;

    public ErrorDTO(String filed, String message) {
        this.filed = filed;
        this.message = message;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
