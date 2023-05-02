package com.example.EasyMarket.Dto.RequestDto;

import com.example.EasyMarket.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CardRequestDto {

    String mobNo;
    String cardNo;

    int cvv;

    Date expiryDate;

    CardType cardType;
}
