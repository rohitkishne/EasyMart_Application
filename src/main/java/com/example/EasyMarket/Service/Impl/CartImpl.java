package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.CartResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.ItemResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.*;
import com.example.EasyMarket.Exception.CardIsNotValidException;
import com.example.EasyMarket.Exception.CartIsEmptyException;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;
import com.example.EasyMarket.Repository.*;
import com.example.EasyMarket.Service.CartService;
import com.example.EasyMarket.Service.OrderedService;
import com.example.EasyMarket.Transformer.CartTransformer;
import com.example.EasyMarket.Transformer.ItemTransformer;
import com.example.EasyMarket.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedService orderedService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private JavaMailSender emailSender;
    @Override
    public CartResponseDto addToCart(int customerId, Item itemSaved) {

        //fetch the detail of customer
        Customer customer = customerRepository.findById(customerId).get();

        //get the customer cart
        Cart cart = customer.getCart();

        //find the total cost of cart items;
        int totalCost = cart.getCartTotal() + itemSaved.getQuantity()*itemSaved.getProduct().getPrice();
        cart.setCartTotal(totalCost);
        cart.getItems().add(itemSaved);
        cart.setNumberOfItems(cart.getItems().size());
        itemSaved.setCart(cart);

        //save to cart
        Cart savedCart = cartRepository.save(cart);


        //prepare a response of cart
        CartResponseDto cartResponseDto = CartTransformer.CartToCardResponseDto(savedCart);

        //all the item added to the cart and add into the cart response
        List<ItemResponseDto> allItemToCart = new ArrayList<>();
        for(Item item : savedCart.getItems())
        {
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
            allItemToCart.add(itemResponseDto);
        }

        cartResponseDto.setItemList(allItemToCart);
        return cartResponseDto;
    }

    @Override
    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerIsNotFoundException, CardIsNotValidException, CartIsEmptyException, ProductIsNotFoundException {
        //Step 1 : take the customer and check id is valid or not
        //Step 2 : check whether card is valid or not
        //Step 3 : Order the items and update the details of items availability as well
        //Step 4 :Check whether product is available or not even the cart is having a number
        //because it can be possible some other person can buy it and the item are not available
        //but you have added only in cart, not buy it so we have check for avail first, then order
        //Step 5 : You have also reset the order in cart of customer as well
        //Step 6 : prepare a order response

        //Step 1 :
        Customer customer;
        try
        {
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();
        }catch (Exception e)
        {
            throw new CustomerIsNotFoundException("Card Id is not valid!!");
        }

        //Step 2:
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if(card == null || (new Date()).compareTo(card.getExpiryDate())>0 ||
                card.getCvv() != checkoutCartRequestDto.getCvv() ||
                card.getCustomer() != customer)
        {
            throw new CardIsNotValidException("Card is not Valid");
        }

        //check whether customer is having empty cart or not
        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0)
        {
            throw new CartIsEmptyException("Cart is Empty, need atleast one item to order.");
        }

        //Step 3:
        try
        {
            Ordered ordered = orderedService.placeOrder(customer, card);

            //if successfully place a order then set the order inside the customer orderlist;
            //otherwise throw an exception
            customer.getOrders().add(ordered);

            //Step 5:
            resetCart(cart);

            //save the order
            Ordered saveOrder = orderRepository.save(ordered);

            //Step 6:
            OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(saveOrder);

            List<ItemResponseDto> items = new ArrayList<>();
            for(Item item : saveOrder.getItems())
            {
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                items.add(itemResponseDto);
            }

            orderResponseDto.setItemList(items);

            String text = "Order Detail : \n";
            text = text + "Order Number : "+orderResponseDto.getOrderNo()+"\n";
            text = text + "Order for : "+orderResponseDto.getCustomerName()+"\n";
            text = text + "Total cost for Order : "+orderResponseDto.getTotalCost();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("projectpurposetesting@gmail.com");
            message.setTo(cart.getCustomer().getEmail());
            message.setSubject("Product Ordered");
            message.setText(text);
            emailSender.send(message);

            return orderResponseDto;
        }
        catch (Exception e)
        {
            throw new ProductIsNotFoundException("Product are not available");
        }

    }

    @Override
    public List<ItemResponseDto> viewAllItemInCart(int id) throws CustomerIsNotFoundException {

        Cart cart;
        try{
            cart = cartRepository.findById(id).get();
        }catch (Exception e)
        {
            throw new CustomerIsNotFoundException("Cart is not Exist");
        }
        List<Item> itemList= cart.getItems();
        List<ItemResponseDto> viewCartItemDetail = new ArrayList<>();
        for(Item item : itemList)
        {
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
            viewCartItemDetail.add(itemResponseDto);
        }

        return viewCartItemDetail;
    }

    @Override
    public String removeItemFromCart(int id) {

        //get the item
        Item item = itemRepository.findById(id).get();
        Cart cart = item.getCart();

        int remainingAmount = cart.getCartTotal() - item.getQuantity() * item.getProduct().getPrice();
        cart.setCartTotal(remainingAmount);

        int remainingItem = cart.getNumberOfItems() - item.getQuantity();
        cart.setNumberOfItems(remainingItem);

        itemRepository.delete(item);

        return "Item from cart belongs to "+cart.getCustomer().getName()+" removed Successfully";
    }

    public void resetCart(Cart cart)
    {
        cart.setCartTotal(0);
        for(Item item : cart.getItems())
        {
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.getItems().clear();
    }
}
