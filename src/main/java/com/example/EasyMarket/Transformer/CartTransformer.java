package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.ResponseDto.CartResponseDto;
import com.example.EasyMarket.Entity.Cart;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartTransformer {

    public static CartResponseDto CartToCardResponseDto(Cart cart)
    {
        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .noOfItems(cart.getNumberOfItems())
                .cartTotal(cart.getCartTotal())
                .build();
    }
}
