package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto)
    {
        return customerService.addCustomer(customerRequestDto);
    }

    @GetMapping("/getAllCustomer")
    public List<CustomerResponseDto> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/getCustomerByEmail")
    public CustomerResponseDto getCustomerByEmail(@RequestParam String email)
    {
        return customerService.getCustomerByEmail(email);
    }

    @GetMapping("/getCustomerByMobNo")
    public CustomerResponseDto getCustomerByMobNo(@RequestParam String mobNo)
    {
        return customerService.getCustomerByMobNo(mobNo);
    }

    @GetMapping("/getCustomerByMoreThan/{age}")
    public List<CustomerResponseDto> getCustomerByMoreThanGivenAge(@PathVariable("age") int age)
    {
        return customerService.getCustomerByMoreThanGivenAge(age);
    }

    @GetMapping("/getAllCustomerHavingVisaCard")
    public List<CustomerResponseDto> getAllCustomerHavingVisaCard()
    {
        return customerService.getAllCustomerHavingVisaCard();
    }

    @PutMapping("/updateCustomerInfoByEmail")
    public CustomerResponseDto updateCustomerInfoByEmail(@RequestParam String email,@RequestBody CustomerRequestDto customerRequestDto) throws CustomerIsNotFoundException {
        return customerService.updateCustomerInfoByEmail(email, customerRequestDto);
    }

    @DeleteMapping("/deleteCustomerByEmail")
    public String deleteCustomerByEmail(@RequestParam String email)
    {
        return customerService.deleteCustomerByEmail(email);
    }

    @DeleteMapping("/deleteCustomerByMobNo")
    public String deleteCustomerByMobNo(@RequestParam String mobNo)
    {
        return customerService.deleteCustomerByMobNo(mobNo);
    }
}
