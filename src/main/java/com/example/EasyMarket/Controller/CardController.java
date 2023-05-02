package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Entity.Card;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("addCard")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponse = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
