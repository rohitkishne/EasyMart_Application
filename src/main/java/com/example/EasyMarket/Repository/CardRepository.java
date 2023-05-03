package com.example.EasyMarket.Repository;

import com.example.EasyMarket.Entity.Card;
import com.example.EasyMarket.Enum.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCardNo(String cardNo);

    List<Card> findByCardType(CardType cardType);
    @Query(value = "select c from Card c where c.cardType = :cardType and c.expiryDate <= :date")
    List<Card> findAllCardByLessThanExpiryDate(CardType cardType, Date date);

    @Query(value = "select c from Card c where c.cardType = :cardType and c.expiryDate >= :date")
    List<Card> findAllCardByMoreThanExpiryDate(CardType cardType, Date date);

    @Query(value = "select max(c.cardType) from Card c group by c.cardType")
    CardType findCartTypeWithHighestNo();
    @Query(value = "select count(c.cardType) from Card c group by c.cardType")
    int findCartTypeNoWithHighestNo();
}
