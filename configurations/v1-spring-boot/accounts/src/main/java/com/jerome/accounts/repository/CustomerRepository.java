package com.jerome.accounts.repository;

import com.jerome.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// extending to JPA repository to access predefined methods
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // having custom method, to query the mobile number
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
