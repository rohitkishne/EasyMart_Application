package com.example.EasyMarket.Repository;

import com.example.EasyMarket.Entity.Ordered;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {

    @Query(value = "select o from Ordered o where o.customer.id = :id")
    List<Ordered> findAllOrderOfCustomer(int id);

    @Query(value = "select o from Ordered o Order By o.orderDate Desc limit 5")
    List<Ordered> findFiveRecentOrder();

    @Query(value = "select o from Ordered o Order By o.totalCost Desc limit 1")
    Ordered findTopOrder();
}
