package com.danicode.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LambdaBank implements RequestHandler<BankRequest, BankResponse> {

    @Override
    public BankResponse handleRequest(BankRequest bankRequest, Context context) {
        // definir el contexto matematico => para la exactitud de los calculos
        MathContext mathContext = MathContext.DECIMAL128; // precision 34
        BigDecimal amount = bankRequest.getAmmount().setScale(2, RoundingMode.HALF_UP); // dos decimales
        BigDecimal rate = bankRequest.getRate()
                .divide(BigDecimal.valueOf(100), mathContext)
                .setScale(2, RoundingMode.HALF_UP);
        Integer term = bankRequest.getTerm();
        // Con cuanta de ahorro
        // Si tiene cuenta, restar al monto 0.2
        BigDecimal rateWithAccount = bankRequest.getRate()
                .subtract(BigDecimal.valueOf(0.2), mathContext)
                .divide(BigDecimal.valueOf(100), mathContext)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal quotaWithoutAccount = this.calculateQuota(amount, rate, term, mathContext);
        BigDecimal quotaWithAccount = this.calculateQuota(amount, rateWithAccount, term, mathContext);

        BankResponse bankResponse = new BankResponse();
        bankResponse.setQuote(quotaWithoutAccount);
        bankResponse.setRate(rate);
        bankResponse.setTerm(term);

        bankResponse.setQuoteWithAccount(quotaWithAccount);
        bankResponse.setRateWithAccount(rateWithAccount);
        bankResponse.setTermWithAccount(term);

        return bankResponse;
    }

    /**
     * P = Monto del préstamo
     * i = Tasa de interés mensual
     * n = Plazo del crédito en meses
     * <p>
     * Cuota mensual = (P * i) / (1 - (1 + i) ^ (-n))
     */
    public BigDecimal calculateQuota(BigDecimal amount, BigDecimal rate, Integer term, MathContext mathContext) {
        // 1 +i
        BigDecimal onePlusRate = rate.add(BigDecimal.ONE, mathContext);
        // (1 + i)* n
        BigDecimal onePlusRateToN = onePlusRate.pow(term, mathContext);
        // se necesita el reciproco
        BigDecimal onePlusRateToNegativeN = BigDecimal.ONE.divide(onePlusRateToN, mathContext);
        // numerador
        BigDecimal numerator = amount.multiply(rate, mathContext);
        BigDecimal denominator = BigDecimal.ONE.subtract(onePlusRateToNegativeN, mathContext);

        return numerator.divide(denominator, mathContext).setScale(2, RoundingMode.HALF_UP);
    }
}