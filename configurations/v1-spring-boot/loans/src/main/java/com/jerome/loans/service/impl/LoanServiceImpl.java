package com.jerome.loans.service.impl;

import com.jerome.loans.constants.LoansConstants;
import com.jerome.loans.domain.LoansModel;
import com.jerome.loans.dto.LoansDto;
import com.jerome.loans.exceptions.LoanAlreadyExistingException;
import com.jerome.loans.exceptions.ResourceNotFoundException;
import com.jerome.loans.mapper.LoansMapper;
import com.jerome.loans.repository.LoansRepository;
import com.jerome.loans.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoansRepository loansRepository;
    private final LoansMapper loansMapper;


    @Override
    public void createLoan(String mobileNumber) {
        Optional<LoansModel> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()){
            throw new LoanAlreadyExistingException("Loan already exists with registered number: " + mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));

    }

    public LoansModel createNewLoan (String mobileNumber){
        LoansModel loansModel = new LoansModel();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        loansModel.setLoanNumber(Long.toString(randomLoanNumber));
        loansModel.setMobileNumber(mobileNumber);
        loansModel.setLoanType(LoansConstants.HOME_LOAN);
        loansModel.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        loansModel.setAmountPaid(0);
        loansModel.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return loansModel;
    }


    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        LoansModel loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "mobile number", mobileNumber)
        );

        return loansMapper.toDto(loans);
    }


    @Override
    public boolean updateLoan(LoansDto loansDto) {
        LoansModel loans = loansRepository.findByMobileNumber(loansDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobile number", loansDto.getMobileNumber())
        );

        loansMapper.updateLoanFromDto(loansDto, loans);
        loansRepository.save(loans);

        return true;
    }


    @Override
    public boolean deleteLoan(String mobileNumber) {
        LoansModel loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loans", "Mobile number", mobileNumber)
        );

        loansRepository.deleteById(loans.getLoanId());
        return true;
    }
}
