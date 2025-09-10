package com.desafio.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import com.desafio.models.entities.Contract;
import com.desafio.models.entities.Installment;
import com.desafio.services.ContractService;
import com.desafio.services.PaypalService;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato: ");

        System.out.print("Numero: ");
        Integer number = sc.nextInt();
        sc.nextLine();

        System.out.print("Data (dd/MM/yyyy): ");
        String dateStr = sc.nextLine();
        LocalDate date = LocalDate.parse(dateStr,formatter);

        System.out.print("Valor do contrato: ");
        Double totalValue = sc.nextDouble();
        sc.nextLine();

        System.out.print("Entre com o numero de parcelas: ");
        Integer months = sc.nextInt();

        Contract c = new Contract(number, date, totalValue);

        ContractService cs = new ContractService(new PaypalService());
        cs.processContract(c, months);


        for(Installment installment : c.getInstallmentList()){
            System.out.println("Parcelas");
            System.out.println(installment.getDueDate() + " - " + installment.getAmount());
        }
        sc.close();


    }
}
