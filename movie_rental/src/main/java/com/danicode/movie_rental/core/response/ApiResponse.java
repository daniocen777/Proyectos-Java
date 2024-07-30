package com.danicode.movie_rental.core.response;

import com.danicode.movie_rental.core.dto.ErrorDTO;

import java.util.List;

public class ApiResponse<T> {
    private String status;
    private List<ErrorDTO> errors;
    private T results;

    public ApiResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
