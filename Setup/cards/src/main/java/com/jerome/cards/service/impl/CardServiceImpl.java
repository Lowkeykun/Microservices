package com.jerome.cards.service.impl;

import com.jerome.cards.constants.CardsConstants;
import com.jerome.cards.dto.CardDto;
import com.jerome.cards.exception.CardAlreadyExists;
import com.jerome.cards.exception.ResourceNotFoundException;
import com.jerome.cards.mapper.CardMapper;
import com.jerome.cards.model.CardModel;
import com.jerome.cards.repository.CardRepository;
import com.jerome.cards.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private final CardRepository cardRepository;



    /**
     * @param mobileNumber - Input mobileNumber
     */
    @Override
    public void addCard(String mobileNumber) {
        Optional<CardModel> cardOptional = cardRepository.findByMobileNumber(mobileNumber);
        if (cardOptional.isPresent()){
            throw new CardAlreadyExists("Card is already exists!" + mobileNumber);
        }

        cardRepository.save(createCard(mobileNumber));
    }


    private CardModel createCard(String mobileNumber){
        CardModel card = new CardModel();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(Long.toString(randomCardNumber));
        card.setCardType(CardsConstants.CREDIT_CARD);
        card.setMobileNumber(mobileNumber);
        card.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return card;
    }




    /**
     * @param mobileNumber - Input mobileNumber
     * @return Card details based on the mobile number
     */
    @Override
    public CardDto getCard(String mobileNumber) {
        CardModel cardModel = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
        );
        return cardMapper.toDto(cardModel);
    }

    /**
     * @param cardDto - cardDto Object
     * @return boolean indicating if the update of card is successful or not
     */
    @Override
    public boolean updateCard(CardDto cardDto) {
        CardModel cardModel = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "card number", cardDto.getCardNumber())
        );
        cardMapper.updateCardFromDto(cardDto, cardModel);
        cardRepository.save(cardModel);
        return true;
    }

    /**
     * @param mobileNumber - input mobile number
     * @return boolean indicating if the deletion of card is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        CardModel cardModel = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
        );
        cardRepository.deleteById(cardModel.getCardId());
        return true;
    }
}
