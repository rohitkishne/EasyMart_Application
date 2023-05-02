package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.ItemRequestDto;
import com.example.EasyMarket.Entity.Customer;
import com.example.EasyMarket.Entity.Item;
import com.example.EasyMarket.Entity.Product;
import com.example.EasyMarket.Enum.ProductStatus;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;
import com.example.EasyMarket.Repository.CustomerRepository;
import com.example.EasyMarket.Repository.ItemRepository;
import com.example.EasyMarket.Repository.ProductRepository;
import com.example.EasyMarket.Service.ItemService;
import com.example.EasyMarket.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws CustomerIsNotFoundException, ProductIsNotFoundException {

        //check whether a customer is present or not
        Customer customer;
        try
        {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }
        catch (Exception e)
        {
            throw new CustomerIsNotFoundException("You are not registered with us, Please Register with us");
        }

        //check whether product that you select present or not

        Product product;
        try
        {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }
        catch (Exception e)
        {
            throw new ProductIsNotFoundException("Product is not found");
        }

        //check whether an requested quantity is available or not
        if(itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus() != ProductStatus.AVAILABLE)
        {
            throw new ProductIsNotFoundException("Out of Stock");
        }

        //ensure that both customer and product are present
        //so prepare item from itemRequestDto

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setName(product.getName());
        item.setProduct(product);

        //add the item into product;
        product.getItemList().add(item);

        return itemRepository.save(item);
    }
}
