package com.example.EasyMarket.Service;


import com.example.EasyMarket.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CartResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.ItemResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.Item;
import com.example.EasyMarket.Exception.CardIsNotValidException;
import com.example.EasyMarket.Exception.CartIsEmptyException;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;

import java.util.List;

public interface CartService {

    public CartResponseDto addToCart(int customerId, Item itemSaved);

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerIsNotFoundException, CardIsNotValidException, CartIsEmptyException, ProductIsNotFoundException;

    public List<ItemResponseDto> viewAllItemInCart(int id) throws CustomerIsNotFoundException;

    public String removeItemFromCart(int id);
}
