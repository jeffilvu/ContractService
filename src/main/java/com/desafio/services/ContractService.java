package com.desafio.services;

import java.time.LocalDate;

import com.desafio.models.entities.Contract;
import com.desafio.models.entities.Installment;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public OnlinePaymentService getOnlinePaymentService() {
        return onlinePaymentService;
    }

    public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    
    public void processContract(Contract contract, Integer months){
        double installmentValue = contract.getTotalValue()/months;



        for(int i=1; i <= months; i++){
            
            LocalDate dueDate = contract.getDate().plusMonths(i);
            Double totalInstallmentValue = installmentValue + onlinePaymentService.interest(installmentValue, i);

            totalInstallmentValue += onlinePaymentService.paymentFee(totalInstallmentValue);
            
            contract.addInstallment(new Installment(dueDate, totalInstallmentValue));


        }

    }





}
