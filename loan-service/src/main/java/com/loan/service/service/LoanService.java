package com.loan.service.service;

import com.loan.service.entity.Loan;
import com.loan.service.payload.*;

import java.util.List;

public interface LoanService {
    LoanResponse createLoan(LoanRequest loan);

    List<LoanResponse> getAllLoan();

    LoanResponse getLoanById(String id);

    LoanResponse getLoanByCustomerId(String id);

    LoanResponse getLoanByLenderId(String id);

    List<AggregationLender> getLoanByAggregateLender();

    List<AggregationCustomer> getLoanByAggregateCustomer();

    List<AggregationInterest> getLoanByAggregateInterest();
}
