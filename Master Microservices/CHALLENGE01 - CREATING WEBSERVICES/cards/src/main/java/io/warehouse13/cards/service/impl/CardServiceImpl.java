package io.warehouse13.cards.service.impl;

import io.warehouse13.cards.dto.CardDto;
import io.warehouse13.cards.exception.CardAlreadyExistsException;
import io.warehouse13.cards.exception.ResourceNotFoundException;
import io.warehouse13.cards.io.entity.CardEntity;
import io.warehouse13.cards.io.repository.CardRepository;
import io.warehouse13.cards.mapper.CardMapper;
import io.warehouse13.cards.service.CardService;
import io.warehouse13.cards.shared.CardsConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
	private final CardRepository cardRepository;

	/**
	 * @param mobileNumber - Mobile Number of the Customer
	 */
	@Override
	public void createCard(String mobileNumber) {
		Optional<CardEntity> optionalCardEntity= cardRepository.findByMobileNumber(mobileNumber);
		if(optionalCardEntity.isPresent()){
			throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
		}
		cardRepository.save(createNewCard(mobileNumber));
	}

	/**
	 * @param mobileNumber - Mobile Number of the Customer
	 * @return the new card details
	 */
	private CardEntity createNewCard(String mobileNumber) {
		CardEntity newCard = new CardEntity();
		long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
		newCard.setCardNumber(Long.toString(randomCardNumber));
		newCard.setMobileNumber(mobileNumber);
		newCard.setCardType(CardsConstants.CREDIT_CARD);
		newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
		newCard.setAmountUsed(0);
		newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
		return newCard;
	}

	/**
	 *
	 * @param mobileNumber - Input mobile Number
	 * @return Card Details based on a given mobileNumber
	 */
	@Override
	public CardDto fetchCard(String mobileNumber) {
		CardEntity cardEntity = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
		);
		return CardMapper.mapToCardDto(cardEntity, new CardDto());
	}

	/**
	 *
	 * @param cardDto - cardDto Object
	 * @return boolean indicating if the update of card details is successful or not
	 */
	@Override
	public boolean updateCard(CardDto cardDto) {
		CardEntity cardEntity = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
				() -> new ResourceNotFoundException("Card", "CardNumber", cardDto.getCardNumber()));
		CardMapper.mapToCard(cardDto, cardEntity);
		cardRepository.save(cardEntity);
		return  true;
	}

	/**
	 * @param mobileNumber - Input MobileNumber
	 * @return boolean indicating if the delete of card details is successful or not
	 */
	@Override
	public boolean deleteCard(String mobileNumber) {
		CardEntity cardEntity = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
		);
		cardRepository.deleteById(cardEntity.getCardId());
		return true;
	}
}
