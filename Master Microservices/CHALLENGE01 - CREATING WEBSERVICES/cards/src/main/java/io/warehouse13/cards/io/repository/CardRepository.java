package io.warehouse13.cards.io.repository;

import io.warehouse13.cards.io.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByMobileNumber(String mobileNumber);

    Optional<CardEntity> findByCardNumber(String cardNumber);

}
