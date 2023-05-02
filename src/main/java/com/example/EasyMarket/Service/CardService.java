package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Entity.Card;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerIsNotFoundException;
}
