package com.example.EasyMarket.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.availability.AvailabilityState;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {

    String customerName;
    int noOfItems;
    int cartTotal;
    List<ItemResponseDto> itemList;
}
