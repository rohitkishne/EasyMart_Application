package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;
import com.example.EasyMarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto)
    {
        return customerService.addCustomer(customerRequestDto);
    }
}
