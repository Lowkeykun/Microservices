package com.jerome.cards.service;


import com.jerome.cards.dto.CardDto;

public interface CardService {
    /**
     *
     * @param mobileNumber - Input mobileNumber
     */
    void addCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobileNumber
     * @return Card details based on the mobile number
     */
    CardDto getCard(String mobileNumber);

    /**
     *
     * @param cardDto - cardDto Object
     * @return boolean indicating if the update of card is successful or not
     */
    boolean updateCard(CardDto cardDto);

    /**
     *
     * @param mobileNumber - input mobile number
     * @return boolean indicating if the deletion of card is successful or not
     */
    boolean deleteCard(String mobileNumber);
}
