package com.jerome.accounts.service.impl;

import com.jerome.accounts.constants.AccountsConstants;
import com.jerome.accounts.dto.AccountDto;
import com.jerome.accounts.dto.CustomerDto;
import com.jerome.accounts.entity.Accounts;
import com.jerome.accounts.entity.Customer;
import com.jerome.accounts.exceptions.CustomerAlreadyExistsException;
import com.jerome.accounts.exceptions.ResourceNotFoundException;
import com.jerome.accounts.mapper.AccountMapper;
import com.jerome.accounts.mapper.CustomerMapper;
import com.jerome.accounts.repository.AccountRepository;
import com.jerome.accounts.repository.CustomerRepository;
import com.jerome.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;


    /**
     * @param customerDto - customerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto, new Customer());
        // handles exception if there is existing mobile number.
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer number already exists : " + customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param customer - customer object
     * @return the new account detail
     */
    // passing a parameter of customer to get the customer id
    public Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        // generating random account number
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        accounts.setAccountNumber(randomAccountNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;

    }


    /**
     * @param mobileNumber - Input mobileNumber
     * @return AccountDetails based on the given mobile number
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        // validations if mobile number is existing
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        // validations if the customer is valid through checking of customerID
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        // encapsulating important details so we implement mapping entities to DTO
        CustomerDto customerDto = CustomerMapper.toDto(customer, new CustomerDto());
        // populating the details of accounts into the customerDTO
        customerDto.setAccountDto(AccountMapper.toDto(accounts, new AccountDto()));
        return customerDto;
    }

    /**
     * @param customerDto - CustomerDTO object
     * @return boolean if the update of accounts is success or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        // it takes customerDto object as an input which holds customer data and account details via accountDto
        AccountDto accountDto = customerDto.getAccountDto();
        // checks if accountDto is not null
        if (accountDto != null){
            /* if exists it fetches the corresponding accounts entity from the account repository using the account number
               from the accountDto, if not found it will throw ResourceNotFoundException
            */
            Accounts accounts = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "Account Number", accountDto.getAccountNumber().toString())
            );
            // updating the data of accountDto objects into accounts entity and saves it
            AccountMapper.toEntity(accountDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer","Customer ID", customerId.toString())
            );
            CustomerMapper.toEntity(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param mobileNumber - Input mobileNumber
     * @return boolean if the delete of accounts is success or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        /*  it fetches the corresponding customer entity from the customer repository using the mobile number,
            if not found it will throw ResourceNotFoundException
        */
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        // created custom query for deletion of customer ID
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
