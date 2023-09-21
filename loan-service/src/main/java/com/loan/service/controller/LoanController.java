package com.loan.service.controller;

import com.loan.service.entity.Loan;
import com.loan.service.exception.ErrorCodes;
import com.loan.service.payload.LoanRequest;
import com.loan.service.payload.LoanResponse;
import com.loan.service.service.LoanService;
import com.loan.service.utils.ApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(ApiConfig.BASE+ApiConfig.LOANS)
public class LoanController {

    @Autowired
    private LoanService loanService;

    private Logger logger = LoggerFactory.getLogger(LoanController.class);

    //create
    @PostMapping("/add")
    public ResponseEntity<LoanResponse> addLoan(@Valid @RequestBody LoanRequest loan) {
        if (Objects.isNull(loan)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LOAN_REQUEST_CODE);
        }
        return ResponseEntity.ok(loanService.createLoan(loan));
    }

    @GetMapping("/")
    public ResponseEntity<List<LoanResponse>> getAllLoan() {
        return ResponseEntity.ok(loanService.getAllLoan());
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable (name = "loanId") String id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }
}
