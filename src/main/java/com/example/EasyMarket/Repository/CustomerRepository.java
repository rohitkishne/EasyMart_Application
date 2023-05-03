package com.example.EasyMarket.Repository;

import com.example.EasyMarket.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByMobNo(String mobNo);

    Customer findByEmail(String email);

    @Query(value = "select c from Customer c where c.age > :age")
    List<Customer> findByGiveAge(int age);

    @Query(value = "select c from Customer c Join Card c2 on c2.cardType = VISA ")
    List<Customer> findCustomerWithVisa();
}
