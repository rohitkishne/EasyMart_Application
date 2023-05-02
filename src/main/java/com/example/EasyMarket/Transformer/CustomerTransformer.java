package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;
import com.example.EasyMarket.Entity.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto)
    {
        return Customer.builder().
                name(customerRequestDto.getName())
                .email(customerRequestDto.getEmail())
                .address(customerRequestDto.getAddress())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer)
    {
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .mobNo(customer.getMobNo())
                .build();
    }
}
