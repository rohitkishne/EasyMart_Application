package com.example.EasyMarket.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {

    String customerName;
    String orderNo;
    int totalCost;
    Date orderDate;
    String cardUsed;
    List<ItemResponseDto> itemList;
}
