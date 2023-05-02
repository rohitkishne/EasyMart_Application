package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Entity.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto)
    {
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card)
    {
        return CardResponseDto.builder()
                .message("Card Number "+card.getCardNo()+" has been Successfully added to the Customer "+card.getCustomer().getName())
                .build();
    }
}
