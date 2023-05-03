package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.GetAllCardByTypeResponseDto;
import com.example.EasyMarket.Enum.CardType;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;

import java.sql.Date;
import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerIsNotFoundException;

    public List<GetAllCardByTypeResponseDto> getAllVisaCards(CardType cardType);

    public List<GetAllCardByTypeResponseDto> getCardByCardTypeAndLessThanExpiryDate(CardType cardType, Date expiryDate);

    public List<GetAllCardByTypeResponseDto> getCardByCardTypeAndMoreThanExpiryDate(CardType cardType, Date expiryDate);

    public String getCardTypeWithHighestNo();
}
