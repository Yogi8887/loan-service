package com.loan.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregationInterest {
    private String interest;
    private double totalRemainingAmount;
    private double totalInterest;
    private double totalPenalty;
}
