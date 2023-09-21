package com.loan.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {
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
