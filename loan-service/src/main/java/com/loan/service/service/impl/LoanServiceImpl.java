package com.loan.service.service.impl;

import antlr.StringUtils;
import com.loan.service.controller.LoanController;
import com.loan.service.entity.Loan;
import com.loan.service.exception.ErrorCodes;
import com.loan.service.payload.*;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodes.NOT_FOUND);
        }
        all.forEach(loan -> {
            loanResponse.add(modelMapper.map(loan,LoanResponse.class));
        });
        return loanResponse;
    }

    @Override
    public LoanResponse getLoanById(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_ID);
        }
        Loan loan = loanRepository.findByLoanId(id);
        if(loan == null || ObjectUtils.isEmpty(loan)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodes.NOT_FOUND);
        }
        return modelMapper.map(loan,LoanResponse.class);
    }

    @Override
    public LoanResponse getLoanByCustomerId(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.INVALID_ID);
        }
        Loan loan = loanRepository.findByCustomerId(id);
        if(loan == null || ObjectUtils.isEmpty(loan)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        return modelMapper.map(loan,LoanResponse.class);
    }

    @Override
    public LoanResponse getLoanByLenderId(String id) {
        if(id == null || id.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.INVALID_ID);
        }
        Loan loan = loanRepository.findByLenderId(id);
        if(loan == null || ObjectUtils.isEmpty(loan)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        return modelMapper.map(loan,LoanResponse.class);
    }

    @Override
    public List<AggregationLender> getLoanByAggregateLender() {
        List<Object[]> loanByAggregateLender = loanRepository.getLoanByAggregateLender();
        if(loanByAggregateLender == null || ObjectUtils.isEmpty(loanByAggregateLender)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        List<AggregationLender> aggregationLenders = new ArrayList<>();
        loanByAggregateLender.forEach(loan->{
            AggregationLender aggregationLender = new AggregationLender();
            aggregationLender.setLender(loan[0].toString());
            aggregationLender.setTotalRemainingAmount(((Double) loan[1]));
            aggregationLender.setTotalInterest(((Double) loan[2]));
            aggregationLender.setTotalPenalty(((Double) loan[3]));
            aggregationLenders.add(aggregationLender);
        });
        return aggregationLenders;
    }

    @Override
    public List<AggregationCustomer> getLoanByAggregateCustomer() {
        List<Object[]> loanByAggregateCustomer = loanRepository.getLoanByAggregateCustomer();
        if(loanByAggregateCustomer == null || ObjectUtils.isEmpty(loanByAggregateCustomer)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        List<AggregationCustomer> aggregationCustomers = new ArrayList<>();
        loanByAggregateCustomer.forEach(loan->{
            AggregationCustomer aggregationCustomer = new AggregationCustomer();
            aggregationCustomer.setCustomer(loan[0].toString());
            aggregationCustomer.setTotalRemainingAmount(((Double) loan[1]));
            aggregationCustomer.setTotalInterest(((Double) loan[2]));
            aggregationCustomer.setTotalPenalty(((Double) loan[3]));
            aggregationCustomers.add(aggregationCustomer);
        });
        return aggregationCustomers;
    }

    @Override
    public List<AggregationInterest> getLoanByAggregateInterest() {
        List<Object[]> loanByAggregateInterest = loanRepository.getLoanByAggregateInterest();
        if(loanByAggregateInterest == null || ObjectUtils.isEmpty(loanByAggregateInterest)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND);
        }
        List<AggregationInterest> aggregationInterests = new ArrayList<>();
        loanByAggregateInterest.forEach(loan->{
            AggregationInterest aggregationInterest = new AggregationInterest();
            aggregationInterest.setInterest(loan[0].toString());
            aggregationInterest.setTotalRemainingAmount(((Double) loan[1]));
            aggregationInterest.setTotalInterest(((Double) loan[2]));
            aggregationInterest.setTotalPenalty(((Double) loan[3]));
            aggregationInterests.add(aggregationInterest);
        });
        return aggregationInterests;
    }
}
