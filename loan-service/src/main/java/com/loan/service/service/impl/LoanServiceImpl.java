package com.loan.service.service.impl;

import antlr.StringUtils;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<LoanResponse> getAllLoan() {
        List<LoanResponse> loanResponse = new ArrayList<>();
        List<Loan> all = loanRepository.findAll();
        if(all == null || all.isEmpty() ){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        all.forEach(loan -> {
            loanResponse.add(modelMapper.map(loan,LoanResponse.class));
        });
        return loanResponse;
    }

    @Override
    public LoanResponse getLoanById(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.INVALID_ID);
        }
        Loan loan = loanRepository.findByLoanId(id);
        if(loan == null || ObjectUtils.isEmpty(loan)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        return modelMapper.map(loan,LoanResponse.class);
    }
}
