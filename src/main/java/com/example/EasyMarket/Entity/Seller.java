package com.example.EasyMarket.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="seller")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    @Column(unique=true)
    String email;
    Integer age;
    String mobNo;
    @OneToMany(mappedBy="seller", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
}
