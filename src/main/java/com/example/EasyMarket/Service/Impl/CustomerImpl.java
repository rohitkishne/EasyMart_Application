package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.CustomerRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CustomerResponseDto;
import com.example.EasyMarket.Entity.Cart;
import com.example.EasyMarket.Entity.Customer;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Repository.CustomerRepository;
import com.example.EasyMarket.Service.CustomerService;
import com.example.EasyMarket.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CustomerResponseDto> getAllCustomer() {

        List<Customer> customerList = customerRepository.findAll();

        List<CustomerResponseDto> allCustomer = new ArrayList<>();

        for(Customer customer : customerList)
        {
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            allCustomer.add(customerResponseDto);
        }

        return allCustomer;
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email);

        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);

        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomerByMobNo(String mobNo) {

        Customer customer = customerRepository.findByMobNo(mobNo);

        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getCustomerByMoreThanGivenAge(int age) {

        List<Customer> customerList = customerRepository.findByGiveAge(age);

        List<CustomerResponseDto> allCustomerByAge = new ArrayList<>();

        for(Customer customer : customerList)
        {
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            allCustomerByAge.add(customerResponseDto);
        }
        return allCustomerByAge;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomerHavingVisaCard() {

        List<Customer> customerList = customerRepository.findCustomerWithVisa();

        List<CustomerResponseDto> allCustomerByAge = new ArrayList<>();

        for(Customer customer : customerList)
        {
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            allCustomerByAge.add(customerResponseDto);
        }
        return allCustomerByAge;
    }

    @Override
    public CustomerResponseDto updateCustomerInfoByEmail(String email, CustomerRequestDto customerRequestDto) throws CustomerIsNotFoundException {

        Customer customer;
        try
        {
            customer = customerRepository.findByEmail(email);
        }
        catch (Exception e)
        {
            throw new CustomerIsNotFoundException("Customer is not with this email");
        }

        customer.setName(customerRequestDto.getName());
        customer.setEmail(customerRequestDto.getEmail());
        customer.setMobNo(customerRequestDto.getMobNo());
        customer.setAge(customerRequestDto.getAge());
        customer.setAddress(customerRequestDto.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);

        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(updatedCustomer);

        return customerResponseDto;
    }

    @Override
    public String deleteCustomerByEmail(String email) {

        //get the customer
        Customer customer = customerRepository.findByEmail(email);
        //delete the customer
        customerRepository.delete(customer);
        //create the message
        String deleteCustomer = "Customer "+customer.getName()+" with Email Id "+customer.getEmail()+" has been deleted successfully.";
        return deleteCustomer;
    }

    @Override
    public String deleteCustomerByMobNo(String mobNo) {

        //get the customer
        Customer customer = customerRepository.findByMobNo(mobNo);
        //delete the customer
        customerRepository.delete(customer);
        //create the message
        String deleteCustomer = "Customer "+customer.getName()+" with Mobile Number "+customer.getMobNo()+" has been deleted successfully.";

        return deleteCustomer;
    }

}
