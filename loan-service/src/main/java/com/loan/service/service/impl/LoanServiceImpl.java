package com.loan.service.service.impl;

import com.loan.service.controller.LoanController;
import com.loan.service.entity.Loan;
import com.loan.service.exception.ErrorCodes;
import com.loan.service.payload.LoanRequest;
import com.loan.service.payload.LoanResponse;
import com.loan.service.repository.LoanRepository;
import com.loan.service.service.LoanService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ModelMapper modelMapper;
    private Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);
    @Override
    public LoanResponse createLoan(LoanRequest loanRequest) {
        Loan loan = modelMapper.map(loanRequest, Loan.class);
        logger.info(ErrorCodes.DTO_TO_ENTITY);
        return modelMapper.map( loanRepository.save(loan), LoanResponse.class);
    }
}
