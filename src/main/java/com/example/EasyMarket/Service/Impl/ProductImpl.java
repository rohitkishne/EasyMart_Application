package com.example.EasyMarket.Service.Impl;

import com.example.EasyMarket.Dto.RequestDto.ProductRequestDto;
import com.example.EasyMarket.Dto.ResponseDto.ProductResponseDto;
import com.example.EasyMarket.Entity.Item;
import com.example.EasyMarket.Entity.Product;
import com.example.EasyMarket.Entity.Seller;
import com.example.EasyMarket.Enum.ProductCategory;
import com.example.EasyMarket.Enum.ProductStatus;
import com.example.EasyMarket.Exception.OutOfStockException;
import com.example.EasyMarket.Exception.SellerIsNotFoundException;
import com.example.EasyMarket.Repository.ProductRepository;
import com.example.EasyMarket.Repository.SellerRepository;
import com.example.EasyMarket.Service.ProductService;
import com.example.EasyMarket.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerIsNotFoundException {

        //check whether seller is present or not

        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch(Exception e)
        {
            throw new SellerIsNotFoundException("Seller is not found");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        //now add a product in seller as well
        seller.getProducts().add(product);

        //save the seller will automatically save the product as well
        sellerRepository.save(seller);

        //prepare a responseDto of product

        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProductByCategory(ProductCategory productCategory) {

            // First of all get all the product by category
            List<Product> products =  productRepository.findByCategory(productCategory);

            // create new list to store all product of that category
            List<ProductResponseDto> productResponse = new ArrayList<>();

            for(Product product : products)
            {
                //prepare a response and add to the list
                productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
            }
        return productResponse;
    }

    @Override
    public List<ProductResponseDto> getAllProductByPriceAndCategory(int price, ProductCategory category) {

        //get All the product by category and price
        List<Product> products = productRepository.findByPriceAndCategory(price, category);

        // create new list to store all product of that category
        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponse;
    }

    @Override
    public List<ProductResponseDto> getAllProductBySellerEmailId(String email) throws SellerIsNotFoundException {
        //check whether seller is present or not
        Seller seller;
        try{
            seller = sellerRepository.findByEmail(email);
        }
        catch(Exception e)
        {
            throw new SellerIsNotFoundException("Seller is not found");
        }

        //seller is present now --> get the list of product

        List<Product> products = seller.getProducts();

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponse;
    }

    @Override
    public String deleteBySellerId(int id) throws SellerIsNotFoundException {

        //check whether seller is present or not
        Seller seller;
        try{
            seller = sellerRepository.findById(id).get();
        }
        catch(Exception e)
        {
            throw new SellerIsNotFoundException("Seller is not found");
        }

        //get all list of product
        List<Product> products = productRepository.findAll();
        for(Product product:products)
        {
            //check whether product is corresponding to sellerId
            //if yes --> delete it
            //otherwise --> check for other
            if(product.getSeller().getId() == id)
            {
                productRepository.delete(product);
            }
        }

        //after saving the seller
        sellerRepository.save(seller);

        return "Products deleted Successfully by seller id " + id;
    }

    @Override
    public String deleteByProductId(int id) {
        productRepository.deleteById(id);
        return "Product deleted Successfully";
    }

    @Override
    public List<ProductResponseDto> getFiveCheapestProduct(ProductCategory productCategory) {

        //get the list of top 5 cheapest product
        List<Product> products = productRepository.findByCategoryTopFiveCheapestProduct(productCategory);

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponse;

    }

    @Override
    public List<ProductResponseDto> getFiveCostlyProduct(ProductCategory productCategory) {

        //get the list of top 5 costly product
        List<Product> products = productRepository.findByCategoryTopFiveCostlyProduct(productCategory);

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponse;
    }

    @Override
    public List<ProductResponseDto> getAllAvailableProduct() {

        //get All product
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            if(product.getProductStatus().equals(ProductStatus.AVAILABLE))
            {
                productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
            }
        }

        return productResponse;
    }

    @Override
    public List<ProductResponseDto> getAllOutOfStockProduct() {

        //get All product
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            if(product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK))
            {
                productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
            }
        }

        return productResponse;
    }

    @Override
    public List<ProductResponseDto> getAllProductQuantityLessThanTen() {

        //get all product
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //check whether it is having quantity less than 10 or not
            //if yes --> add to list
            // otherwise --> check to other product
            if (product.getQuantity()<10)
            {
                //prepare a response and add to the list
                productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
            }

        }

        return productResponse;
    }

    @Override
    public ProductResponseDto getCheapestProductInCategory(ProductCategory productCategory) {

        //get the cheapest product
        Product product = productRepository.findCheapestProduct(productCategory);

        //prepare a ProductResponse and return
        return ProductTransformer.ProductToProductResponseDto(product);

    }

    @Override
    public ProductResponseDto getCostlyProductInCategory(ProductCategory productCategory) {

        //get the costly product
        Product product = productRepository.findCostlyProduct(productCategory);

        //prepare a ProductResponse and return
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getTopFiveCostlyProductWithoutCategory() {

        //get the list of top 5 costly product
        List<Product> products = productRepository.findTopFiveCostlyProductWithoutCategory();

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponse;
    }

    @Override
    public List<ProductResponseDto> getTopFiveCheapestProductWithoutCategory() {

        //get the list of top 5 cheapest product
        List<Product> products = productRepository.findTopFiveCheapestProductWithoutCategory();

        List<ProductResponseDto> productResponse = new ArrayList<>();

        for(Product product : products)
        {
            //prepare a response and add to the list
            productResponse.add(ProductTransformer.ProductToProductResponseDto(product));
        }

        return productResponse;
    }

    @Override
    public void decreaseProductQuantity(Item item) throws OutOfStockException {

        Product product = item.getProduct();
        int orderQuantity = item.getQuantity();
        int availQuantity = product.getQuantity();

        //check whether required order quantity avail or not
        if(orderQuantity>availQuantity)
        {
            throw new OutOfStockException("Product Out of Stock");
        }

        //update the product quantity if not out of stock
        product.setQuantity(availQuantity-orderQuantity);

        //if product quantity become 0
        // set product status is Out of Stock;
        if(product.getQuantity()==0)
        {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
    }


}
