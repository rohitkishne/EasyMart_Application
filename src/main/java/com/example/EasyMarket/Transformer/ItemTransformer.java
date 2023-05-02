package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.RequestDto.ItemRequestDto;
import com.example.EasyMarket.Dto.RequestDto.OrderRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.ItemResponseDto;
import com.example.EasyMarket.Entity.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTransformer {

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto)
    {
        return Item.builder()
                .quantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item)
    {
        return ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .priceOfOneItem(item.getProduct().getPrice())
                .totalPrice(item.getQuantity()*item.getProduct().getPrice())
                .quantity(item.getQuantity())
                .build();
    }
}
