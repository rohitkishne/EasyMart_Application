package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.GetAllCardByTypeResponseDto;
import com.example.EasyMarket.Entity.Card;
import com.example.EasyMarket.Entity.Customer;
import com.example.EasyMarket.Enum.CardType;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Repository.CardRepository;
import com.example.EasyMarket.Repository.CustomerRepository;
import com.example.EasyMarket.Service.CardService;
import com.example.EasyMarket.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerIsNotFoundException {
        //check whether customer is present or not
        Customer customer;
        try{
            customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());
        }
        catch (Exception e)
        {
            throw new CustomerIsNotFoundException("Customer is not found");
        }

        //update the card if customer is present
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);

        card.setCustomer(customer);

        customer.getCards().add(card);

        //save the card to customer
        customerRepository.save(customer);

        return CardTransformer.CardToCardResponseDto(card);
    }

    @Override
    public List<GetAllCardByTypeResponseDto> getAllVisaCards(CardType cardType) {

        List<Card> cardList = cardRepository.findByCardType(cardType);

        List<GetAllCardByTypeResponseDto> allCards = new ArrayList<>();
        for(Card card : cardList)
        {
            GetAllCardByTypeResponseDto GetAllCardByTypeResponseDto = CardTransformer.CardToGetAllCardByTypeResponseDto(card);
            allCards.add(GetAllCardByTypeResponseDto);
        }
        return allCards;
    }

    @Override
    public List<GetAllCardByTypeResponseDto> getCardByCardTypeAndLessThanExpiryDate(CardType cardType, Date expiryDate) {

        List<Card> cardList = cardRepository.findAllCardByLessThanExpiryDate(cardType, expiryDate);

        List<GetAllCardByTypeResponseDto> allCards = new ArrayList<>();
        for(Card card : cardList)
        {
            GetAllCardByTypeResponseDto GetAllCardByTypeResponseDto = CardTransformer.CardToGetAllCardByTypeResponseDto(card);
            allCards.add(GetAllCardByTypeResponseDto);
        }
        return allCards;
    }

    @Override
    public List<GetAllCardByTypeResponseDto> getCardByCardTypeAndMoreThanExpiryDate(CardType cardType, Date expiryDate) {


        List<Card> cardList = cardRepository.findAllCardByMoreThanExpiryDate(cardType, expiryDate);

        List<GetAllCardByTypeResponseDto> allCards = new ArrayList<>();
        for(Card card : cardList)
        {
            GetAllCardByTypeResponseDto GetAllCardByTypeResponseDto = CardTransformer.CardToGetAllCardByTypeResponseDto(card);
            allCards.add(GetAllCardByTypeResponseDto);
        }
        return allCards;
    }

    @Override
    public String getCardTypeWithHighestNo() {

        CardType cardType = cardRepository.findCartTypeWithHighestNo();
        int NoOfmaxCardType = cardRepository.findCartTypeNoWithHighestNo();
        return "Maximum Card is of CardType "+cardType+" and number of "+cardType+" cardType is "+NoOfmaxCardType;
    }
}
