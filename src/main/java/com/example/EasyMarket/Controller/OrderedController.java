package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.OrderRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.Ordered;
import com.example.EasyMarket.Exception.CardIsNotValidException;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;
import com.example.EasyMarket.Service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderedController {

    @Autowired
    OrderedService orderedService;

    @PostMapping("/placeOrder")
    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws CardIsNotValidException, ProductIsNotFoundException, CustomerIsNotFoundException {
        return orderedService.placeOrder(orderRequestDto);
    }

    @GetMapping("/allOrderByCustomerId")
    public List<OrderResponseDto> getAllOrderByCustomerId(@RequestParam int id)
    {
        return orderedService.getAllOrderByCustomerId(id);
    }
    
    @GetMapping("/getFiveRecentOrder")
    public List<OrderResponseDto> getFiveRecentOrder()
    {
        return orderedService.getFiveRecentOrder();
    }

    @DeleteMapping("/deleteOrderById")
    public String deleteOrderById(@RequestParam int id)
    {
        return orderedService.deleteOrderById(id);
    }

    @GetMapping("/getTopOrder")
    public String getTopOrder()
    {
        return orderedService.getTopOrder();
    }

}
