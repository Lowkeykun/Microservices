package com.jerome.loans.repository;

import com.jerome.loans.domain.LoansModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<LoansModel, Long> {
    Optional<LoansModel> findByMobileNumber(String mobileNumber);
    Optional<LoansModel> findByLoanNumber(String loanNumber);
}
