package com.example.EasyMarket.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String email;
    Integer age;
    String mobNo;
    String address;
    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

    @OneToOne(mappedBy="customer", cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL)
    List<Ordered> orders = new ArrayList<>();

}
