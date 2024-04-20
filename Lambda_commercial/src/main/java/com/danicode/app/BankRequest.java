package com.danicode.app;

import java.math.BigDecimal;

public class BankRequest {
    private BigDecimal amount;
    private Integer term; // timpo
    private BigDecimal rate; // % interes

    public BankRequest() {
    }

    public BigDecimal getAmmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
