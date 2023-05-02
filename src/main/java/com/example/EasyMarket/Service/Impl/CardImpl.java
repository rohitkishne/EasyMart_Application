package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Entity.Card;
import com.example.EasyMarket.Entity.Customer;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Repository.CardRepository;
import com.example.EasyMarket.Repository.CustomerRepository;
import com.example.EasyMarket.Service.CardService;
import com.example.EasyMarket.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
