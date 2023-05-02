package com.example.EasyMarket.Dto.ResponseDto;

import com.example.EasyMarket.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {

    String name;
    String sellerName;

    int quantity;
    int price;
    ProductStatus productStatus;


}
