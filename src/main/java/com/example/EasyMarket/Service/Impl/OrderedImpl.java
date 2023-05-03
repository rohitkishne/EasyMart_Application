package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.OrderRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.ItemResponseDto;
import com.example.EasyMarket.Dto.ResponseDto.OrderResponseDto;
import com.example.EasyMarket.Entity.*;
import com.example.EasyMarket.Exception.CardIsNotValidException;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;
import com.example.EasyMarket.Repository.*;
import com.example.EasyMarket.Service.OrderedService;
import com.example.EasyMarket.Service.ProductService;
import com.example.EasyMarket.Transformer.ItemTransformer;
import com.example.EasyMarket.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderedImpl implements OrderedService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ProductService productService;

    @Autowired
    JavaMailSender emailSender;

    //Direct order
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerIsNotFoundException, ProductIsNotFoundException, CardIsNotValidException {
        //check whether a customer is present or not
        Customer customer;
        try
        {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerIsNotFoundException("You are not registered with us, Please Register with us");
        }

        //check whether product that you select present or not

        Product product;
        try
        {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductIsNotFoundException("Product is not found");
        }

        //check whether card is valid or not
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card == null || (new Date()).compareTo(card.getExpiryDate())>0 ||
                card.getCvv() != orderRequestDto.getCvv() ||
                card.getCustomer() != customer)
        {
            throw new CardIsNotValidException("Card is not Valid");
        }

        Item item = Item.builder()
                .name(product.getName())
                .quantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();


        //check item is available or not
        try
        {
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e)
        {
            throw new ProductIsNotFoundException("Product is Out_of_Stock");
        }

        //Prepare Order
        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNumber = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNumber);
        order.setCustomer(customer);
        order.setTotalCost(item.getQuantity()*product.getPrice());
        order.getItems().add(item);

        customer.getOrders().add(order);
        item.setOrdered(order);
        product.getItemList().add(item);

        Ordered saveOrder = orderRepository.save(order);


        //prepare Response of Order

        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(saveOrder);

        List<ItemResponseDto> items = new ArrayList<>();
        for(Item itemEntity : saveOrder.getItems())
        {
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(itemEntity);
            items.add(itemResponseDto);
        }

        orderResponseDto.setItemList(items);

        String text = "Order Detail : \n";
        text = text + "Order Number : "+orderResponseDto.getOrderNo()+"\n";
        text = text + "Order for : "+orderResponseDto.getCustomerName()+"\n";
        text = text + "Total cost for Order : "+orderResponseDto.getTotalCost();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("projectpurposetesting@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Product Ordered");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDto;
    }

    //Order from cart
    @Override
    public Ordered placeOrder(Customer customer, Card card) throws ProductIsNotFoundException {

        Cart cart = customer.getCart();

        Ordered order = new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNumber = generateMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNumber);
        order.setCustomer(customer);
        order.setTotalCost(cart.getCartTotal());

        List<Item> itemList = new ArrayList<>();
        for(Item item : cart.getItems())
        {
            try
            {
                productService.decreaseProductQuantity(item);
                itemList.add(item);
            }
            catch (Exception e)
            {
                throw new ProductIsNotFoundException("Product is Out_of_Stock");
            }
        }

        order.setItems(itemList);

        for(Item item : itemList) {
            item.setOrdered(order);
        }

        return order;
    }

    @Override
    public List<OrderResponseDto> getAllOrderByCustomerId(int id) {

        List<Ordered> orderedList = orderRepository.findAllOrderOfCustomer(id);
        List<OrderResponseDto> allOrder = new ArrayList<>();
        for(Ordered order : orderedList)
        {
            OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(order);
            List<Item> itemList = order.getItems();
            List<ItemResponseDto> items = new ArrayList<>();
            for(Item item : itemList)
            {
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItemList(items);
            allOrder.add(orderResponseDto);
        }

        return allOrder;
    }

    @Override
    public List<OrderResponseDto> getFiveRecentOrder() {

        List<Ordered> orderedList = orderRepository.findFiveRecentOrder();

        List<OrderResponseDto> recentOrder = new ArrayList<>();
        for(Ordered ordered : orderedList)
        {
            OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDto(ordered);
            List<Item> itemList = ordered.getItems();
            List<ItemResponseDto> items = new ArrayList<>();
            for(Item item : itemList)
            {
                ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
                items.add(itemResponseDto);
            }
            orderResponseDto.setItemList(items);
            recentOrder.add(orderResponseDto);
        }
        return recentOrder;
    }

    @Override
    public String deleteOrderById(int id) {

        orderRepository.deleteById(id);
        return "Order deleted Successfully";
    }

    @Override
    public String getTopOrder() {

        Ordered ordered = orderRepository.findTopOrder();
        String topCustomer = "Customer "+ordered.getCustomer().getName()+" for order of amount "+ordered.getTotalCost()+" is Valued Customer for EasyMart";
        return topCustomer;
    }

    public String generateMaskedCard(String cardNo)
    {
        String maskCardNo = "";

        for(int i=0; i<cardNo.length()-4; i++)
        {
            maskCardNo += "X"; // hide the card no by X except last 4 digit;
        }

        maskCardNo = maskCardNo + cardNo.substring(cardNo.length()-4);

        return maskCardNo;
    }
}
