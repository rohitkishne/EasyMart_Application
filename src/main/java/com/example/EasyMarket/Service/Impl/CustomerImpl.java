package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;
import com.example.EasyMarket.Entity.Cart;
import com.example.EasyMarket.Entity.Customer;
import com.example.EasyMarket.Repository.CustomerRepository;
import com.example.EasyMarket.Service.CustomerService;
import com.example.EasyMarket.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();

        customer.setCart(cart);
        Customer addedCustomer = customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(addedCustomer);
    }
}
