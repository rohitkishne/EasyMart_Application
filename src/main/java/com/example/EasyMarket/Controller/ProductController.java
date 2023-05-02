package com.example.EasyMarket.Controller;

import com.example.EasyMarket.Dto.RequestDto.ProductRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.ProductResponseDto;
import com.example.EasyMarket.Enum.ProductCategory;
import com.example.EasyMarket.Exception.SellerIsNotFoundException;
import com.example.EasyMarket.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) throws SellerIsNotFoundException {
        return productService.addProduct(productRequestDto);
    }

    @GetMapping("/getAllProductByCategory")
    public List<ProductResponseDto> getAllProductByCategory(@RequestParam ProductCategory productCategory)
    {
        return productService.getAllProductByCategory(productCategory);
    }

    @GetMapping("/getAllProductByPriceAndCategory")
    public List<ProductResponseDto> getAllProductByPriceAndCategory(@RequestParam int price, @RequestParam ProductCategory productCategory)
    {
        return productService.getAllProductByPriceAndCategory(price, productCategory);
    }

    @GetMapping("/getAllProductBySellerEmailId")
    public List<ProductResponseDto> getAllProductBySellerEmailId(@RequestParam String email) throws SellerIsNotFoundException {
       return productService.getAllProductBySellerEmailId(email);
    }

    @DeleteMapping("/deleteBySellerId")
    public String deleteBySellerId(@RequestParam int id) throws SellerIsNotFoundException {
        return productService.deleteBySellerId(id);
    }

    @DeleteMapping("/deleteByProductId")
    public String deleteByProductId(@RequestParam int id)
    {
        return productService.deleteByProductId(id);
    }

    @GetMapping("/getFiveCheapestProductByCategory")
    public List<ProductResponseDto> getFiveCheapestProduct(@RequestParam ProductCategory productCategory)
    {
        return productService.getFiveCheapestProduct(productCategory);
    }

    @GetMapping("/getFiveCostlyProductByCategory")
    public List<ProductResponseDto> getFiveCostlyProduct(@RequestParam ProductCategory productCategory)
    {
        return productService.getFiveCostlyProduct(productCategory);
    }

    @GetMapping("/getAllProductQuantityLessThanTen")
    public List<ProductResponseDto> getAllProductQuantityLessThanTen()
    {
        return productService.getAllProductQuantityLessThanTen();
    }

    @GetMapping("/getAllAvailableProduct")
    public List<ProductResponseDto> getAllAvailableProduct(){
        return productService.getAllAvailableProduct();
    }

    @GetMapping("/getAllOutOfStockProduct")
    public List<ProductResponseDto> getAllOutOfStockProduct(){
        return productService.getAllOutOfStockProduct();
    }

    @GetMapping("/getCheapestProductInCategory")
    public ProductResponseDto getCheapestProductInCategory(@RequestParam ProductCategory productCategory)
    {
        return productService.getCheapestProductInCategory(productCategory);
    }

    @GetMapping("/getCostlyProductInCategory")
    public ProductResponseDto getCostlyProductInCategory(@RequestParam ProductCategory productCategory)
    {
        return productService.getCostlyProductInCategory(productCategory);
    }

    @GetMapping("/getTopFiveCostlyProductWithoutCategory")
    public List<ProductResponseDto> getTopFiveCostlyProductWithoutCategory(){
        return productService.getTopFiveCostlyProductWithoutCategory();
    }

    @GetMapping("/getTopFiveCheapestProductWithoutCategory")
    public List<ProductResponseDto> getTopFiveCheapestProductWithoutCategory(){
        return productService.getTopFiveCheapestProductWithoutCategory();
    }
}
