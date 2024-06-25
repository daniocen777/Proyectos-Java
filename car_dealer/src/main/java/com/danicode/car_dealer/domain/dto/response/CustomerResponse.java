package com.danicode.car_dealer.domain.dto.response;

public class CustomerResponse {
    private String password;

    public CustomerResponse() {
    }

    public CustomerResponse(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
