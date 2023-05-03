package com.example.EasyMarket.Dto.ResponseDto;

import com.example.EasyMarket.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class GetAllCardByTypeResponseDto {

    String customerName;
    String cardNo;
    CardType cardType;
    Date expiryDate;
}
