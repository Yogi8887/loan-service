package com.loan.service.repository;

import com.loan.service.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

   Loan findByLoanId(String id);
   Loan findByCustomerId(String id);

   Loan findByLenderId(String id);

   @Query(value = "SELECT lender_id,SUM(remaining_amount) AS total_remaining_amount,SUM(interest_per_day) AS total_interest,SUM(penalty_per_day) AS total_penalty FROM loan GROUP BY lender_id", nativeQuery = true)
   List<Object[]> getLoanByAggregateLender();

   @Query(value = "SELECT customer_id,SUM(remaining_amount) AS total_remaining_amount,SUM(interest_per_day) AS total_interest,SUM(penalty_per_day) AS total_penalty FROM loan GROUP BY customer_id", nativeQuery = true)
   List<Object[]> getLoanByAggregateCustomer();

   @Query(value = "SELECT loan_id ,SUM(remaining_amount) AS total_remaining_amount,SUM(interest_per_day) AS total_interest,SUM(penalty_per_day) AS total_penalty FROM loan GROUP BY loan_id", nativeQuery = true)
   List<Object[]> getLoanByAggregateInterest();
}
