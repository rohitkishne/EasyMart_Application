package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.CardRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CardResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.GetAllCardByTypeResponseDto;
import com.example.EasyMarket.Enum.CardType;
import com.example.EasyMarket.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/addCard")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponse = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponse, HttpStatus.CREATED);
        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCards/{cardType}")
    public List<GetAllCardByTypeResponseDto> getAllVisaCards(@PathVariable("cardType")CardType cardType){
        return cardService.getAllVisaCards(cardType);
    }

    @GetMapping("/getCardByCardTypeAndLessThanExpiryDate/{cardType}/{expiryDate}")
    public List<GetAllCardByTypeResponseDto> getCardByCardTypeAndLessThanExpiryDate(@PathVariable("cardType") CardType cardType, @PathVariable("expiryDate") Date expiryDate)
    {
        return cardService.getCardByCardTypeAndLessThanExpiryDate(cardType, expiryDate);
    }

    @GetMapping("/getCardByCardTypeAndMoreThanExpiryDate/{cardType}/{expiryDate}")
    public List<GetAllCardByTypeResponseDto> getCardByCardTypeAndMoreThanExpiryDate(@PathVariable("cardType") CardType cardType, @PathVariable("expiryDate") Date expiryDate)
    {
        return cardService.getCardByCardTypeAndMoreThanExpiryDate(cardType, expiryDate);
    }

    @GetMapping("/getCardTypeWithHighestNo")
    public String getCardTypeWithHighestNo(){
        return cardService.getCardTypeWithHighestNo();
    }
}
