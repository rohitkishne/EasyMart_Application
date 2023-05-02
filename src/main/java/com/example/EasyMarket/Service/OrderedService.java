package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.OrderRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.Card;
import com.example.EasyMarket.Entity.Customer;
import com.example.EasyMarket.Entity.Ordered;
import com.example.EasyMarket.Exception.CardIsNotValidException;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;

import java.util.List;

public interface OrderedService {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerIsNotFoundException, ProductIsNotFoundException, CardIsNotValidException;

    public Ordered placeOrder(Customer customer, Card card) throws ProductIsNotFoundException;

    public List<OrderResponseDto> getAllOrderByCustomerId(int id);

    public List<OrderResponseDto> getFiveRecentOrder();

    public String deleteOrderById(int id);

    public String getTopOrder();
}
