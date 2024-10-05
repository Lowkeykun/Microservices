package com.jerome.accounts.mapper;

import com.jerome.accounts.dto.CustomerDto;
import com.jerome.accounts.entity.Customer;

public class CustomerMapper {

    // Entity to DTO
    public static CustomerDto toDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    // DTO to Entity
    public static Customer toEntity(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
