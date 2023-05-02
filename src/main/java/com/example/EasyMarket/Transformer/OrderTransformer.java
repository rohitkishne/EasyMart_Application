package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.Ordered;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(Ordered ordered)
    {
        return OrderResponseDto.builder()
                .customerName(ordered.getCustomer().getName())
                .orderDate(ordered.getOrderDate())
                .orderNo(ordered.getOrderNo())
                .totalCost(ordered.getTotalCost())
                .cardUsed(ordered.getCardUsed())
                .build();
    }
}
