package com.desafio.services;


public class PaypalService implements OnlinePaymentService{

    @Override
    public Double paymentFee(Double amount) {
        Double fee = amount * 0.02;
        return fee;
    }

    @Override
    public Double interest(Double amount, Integer months) {
        Double interest = ((amount * 0.01) * months);
        return interest;

    }

}
