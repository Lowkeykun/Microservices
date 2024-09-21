package com.jerome.loans.service;

import com.jerome.loans.dto.LoansDto;

public interface LoanService {

    /**
     *
     * @param mobileNumber - input mobile number
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - input mobile number
     * @return Loan details based on input mobile number
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - loanDto object
     * @return boolean indicating if update is successful or failed
     */
    boolean updateLoan(LoansDto loansDto);


    /**
     *
     * @param mobileNumber - input mobile number
     * @return boolean indicating if delete is successful or failed
     */
    boolean deleteLoan(String mobileNumber);
}
