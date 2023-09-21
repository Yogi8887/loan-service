package com.loan.service.service;

import com.loan.service.entity.Loan;
import com.loan.service.payload.LoanRequest;
import com.loan.service.payload.LoanResponse;

public interface LoanService {
    LoanResponse createLoan(LoanRequest loan);
}
