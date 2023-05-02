package com.example.EasyMarket.Service;

import com.example.EasyMarket.Dto.RequestDto.ProductRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.ProductResponseDto;
import com.example.EasyMarket.Entity.Item;
import com.example.EasyMarket.Entity.Product;
import com.example.EasyMarket.Enum.ProductCategory;
import com.example.EasyMarket.Exception.OutOfStockException;
import com.example.EasyMarket.Exception.SellerIsNotFoundException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerIsNotFoundException;

    public List<ProductResponseDto> getAllProductByCategory(ProductCategory productCategory);

    public List<ProductResponseDto> getAllProductByPriceAndCategory(int price, ProductCategory productCategory);

    public List<ProductResponseDto> getAllProductBySellerEmailId(String email) throws SellerIsNotFoundException;

    public String deleteBySellerId(int id) throws SellerIsNotFoundException;

    public String deleteByProductId(int id);

    public List<ProductResponseDto> getFiveCheapestProduct(ProductCategory productCategory);

    public List<ProductResponseDto> getFiveCostlyProduct(ProductCategory productCategory);

    public List<ProductResponseDto> getAllAvailableProduct();

    public List<ProductResponseDto> getAllOutOfStockProduct();

    public List<ProductResponseDto> getAllProductQuantityLessThanTen();

    public ProductResponseDto getCheapestProductInCategory(ProductCategory productCategory);

    public ProductResponseDto getCostlyProductInCategory(ProductCategory productCategory);

    public List<ProductResponseDto> getTopFiveCostlyProductWithoutCategory();

    public List<ProductResponseDto> getTopFiveCheapestProductWithoutCategory();

    public void decreaseProductQuantity(Item item) throws OutOfStockException;
}
