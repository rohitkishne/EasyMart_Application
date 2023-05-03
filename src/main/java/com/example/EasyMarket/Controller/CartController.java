package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.EasyMarket.Dto.RequestDto.ItemRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CartResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.ItemResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.Item;
import com.example.EasyMarket.Exception.CardIsNotValidException;
import com.example.EasyMarket.Exception.CartIsEmptyException;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;
import com.example.EasyMarket.Service.CartService;
import com.example.EasyMarket.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @PostMapping("/addItemsToCart")
    public ResponseEntity addItemsToCart(@RequestBody ItemRequestDto itemRequestDto)
    {
        try
        {
            Item itemSaved = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(itemRequestDto.getCustomerId(), itemSaved);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);

        }catch (Exception e)
        {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto) throws CardIsNotValidException, CustomerIsNotFoundException, CartIsEmptyException, ProductIsNotFoundException {
        return cartService.checkOutCart(checkoutCartRequestDto);
    }

    @GetMapping("/viewAllItemInCart")
    public List<ItemResponseDto> viewAllItemInCart(@RequestParam int id) throws CustomerIsNotFoundException {
        return cartService.viewAllItemInCart(id);
    }

    @DeleteMapping("/removeItemFromCart")
    public String removeItemFromCart(@RequestParam int id)
    {
        return cartService.removeItemFromCart(id);
    }
}
