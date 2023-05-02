package com.example.EasyMarket.Dto.RequestDto;

import com.example.EasyMarket.Enum.ProductCategory;
import com.example.EasyMarket.Enum.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ProductRequestDto {

    int sellerId;
    String name;
    int quantity;
    int price;

    ProductCategory category;
}
