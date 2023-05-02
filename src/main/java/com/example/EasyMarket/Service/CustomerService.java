package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
