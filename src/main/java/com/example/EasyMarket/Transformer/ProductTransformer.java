package com.example.EasyMarket.Transformer;

import com.example.EasyMarket.Dto.RequestDto.ProductRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.ProductResponseDto;
import com.example.EasyMarket.Entity.Product;
import com.example.EasyMarket.Enum.ProductStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        return Product.builder().
                name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .category(productRequestDto.getCategory())
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product)
    {
        return ProductResponseDto.builder()
                .name(product.getName())
                .sellerName(product.getSeller().getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productStatus(product.getProductStatus()).
                build();
    }
}
