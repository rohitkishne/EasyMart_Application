package com.example.EasyMarket.Repository;


import com.example.EasyMarket.Entity.Product;
import com.example.EasyMarket.Enum.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(ProductCategory category);

    @Query(value="select p from Product p where p.price > :price and p.category = :category")
    List<Product> findByPriceAndCategory(int price, ProductCategory category);

    @Query(value = "select p from Product p where p.category = :category Order By p.price Asc limit 5")
    List<Product> findByCategoryTopFiveCheapestProduct(ProductCategory category);

    @Query(value = "select p from Product p where p.category = :category Order By p.price Desc limit 5")
    List<Product> findByCategoryTopFiveCostlyProduct(ProductCategory category);

    @Query(value = "select p from Product p where p.category = :category Order By p.price Asc limit 1")
    Product findCheapestProduct(ProductCategory category);

    @Query(value = "select p from Product p where p.category = :category Order By p.price Desc limit 1")
    Product findCostlyProduct(ProductCategory category);

    @Query(value = "select p from Product p Order By p.price Desc limit 5")
    List<Product> findTopFiveCostlyProductWithoutCategory();

    @Query(value = "select p from Product p Order By p.price Asc limit 5")
    List<Product> findTopFiveCheapestProductWithoutCategory();
}
