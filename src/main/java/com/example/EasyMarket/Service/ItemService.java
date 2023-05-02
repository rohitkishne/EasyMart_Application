package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.ItemRequestDto;
import com.example.EasyMarket.Entity.Item;
import com.example.EasyMarket.Exception.CustomerIsNotFoundException;
import com.example.EasyMarket.Exception.ProductIsNotFoundException;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws CustomerIsNotFoundException, ProductIsNotFoundException;
}
