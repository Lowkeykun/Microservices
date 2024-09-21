package com.jerome.cards.repository;

import com.jerome.cards.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardModel, Long> {
    Optional<CardModel> findByMobileNumber(String mobileNumber);
    Optional<CardModel> findByCardNumber(String cardNumber);
}
