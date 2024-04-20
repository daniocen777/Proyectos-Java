package com.danicode.app;

import java.math.BigDecimal;

public class BankResponse {
    private BigDecimal quote;
    private BigDecimal rate;
    private Integer term;

    // si abre una cuanta de ahorros
    private BigDecimal quoteWithAccount;
    private BigDecimal rateWithAccount;
    private Integer termWithAccount;

    public BankResponse() {
    }

    public BigDecimal getQuote() {
        return quote;
    }

    public void setQuote(BigDecimal quote) {
        this.quote = quote;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getQuoteWithAccount() {
        return quoteWithAccount;
    }

    public void setQuoteWithAccount(BigDecimal quoteWithAccount) {
        this.quoteWithAccount = quoteWithAccount;
    }

    public BigDecimal getRateWithAccount() {
        return rateWithAccount;
    }

    public void setRateWithAccount(BigDecimal rateWithAccount) {
        this.rateWithAccount = rateWithAccount;
    }

    public Integer getTermWithAccount() {
        return termWithAccount;
    }

    public void setTermWithAccount(Integer termWithAccount) {
        this.termWithAccount = termWithAccount;
    }
}
