package com.jerome.accounts.repository;

import com.jerome.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// extending to JPA repository to access predefined methods
@Repository
public interface AccountRepository extends JpaRepository<Accounts, Long> {
    // added custom method, to query the customer ID
    Optional<Accounts> findByCustomerId(long customerId);

    // We need to include these annotations because jpa is not smart to know custom queries
    @Transactional // to rollback
    @Modifying // to inform we're performing a modification (deleting)
    void deleteByCustomerId(long customerId);
}
