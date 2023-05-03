package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    public List<CustomerResponseDto> getAllCustomer();

    public CustomerResponseDto getCustomerByEmail(String email);

    public CustomerResponseDto getCustomerByMobNo(String mobNo);

    public List<CustomerResponseDto> getCustomerByMoreThanGivenAge(int age);

    public List<CustomerResponseDto> getAllCustomerHavingVisaCard();

    public CustomerResponseDto updateCustomerInfoByEmail(String email, CustomerRequestDto customerRequestDto) throws CustomerIsNotFoundException;

    public String deleteCustomerByEmail(String email);

    public String deleteCustomerByMobNo(String mobNo);
}
