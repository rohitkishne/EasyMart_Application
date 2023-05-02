package com.example.EasyMarket.Dto.RequestDto;

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
public class OrderRequestDto {

    int CustomerId;
    int productId;
    Date orderDate;
    String cardNo;
    int cvv;

    int requiredQuantity;

}
