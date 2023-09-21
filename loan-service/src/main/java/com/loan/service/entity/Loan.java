package com.loan.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    private String loanId;
    private String customerId;
    private String lenderId;
    private double amount;
    private double remainingAmount;
    private String paymentDate;
    private float interestPerDay;
    private String dueDate;
    private float penaltyPerDay;
    private boolean cancel;

}
