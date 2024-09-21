package com.jerome.accounts.service;

import com.jerome.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *
     * @param customerDto - customerDto object
     */
    void createAccount(CustomerDto customerDto);


    /**
     *
     * @param mobileNumber - Input mobileNumber
     * @return AccountDetails based on the given mobile number
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - CustomerDTO object
     * @return boolean if the update of accounts is success or not
     */
    boolean updateAccount(CustomerDto customerDto);


    /**
     *
     * @param mobileNumber - Input mobileNumber
     * @return boolean if the delete of accounts is success or not
     */
    boolean deleteAccount(String mobileNumber);
}
