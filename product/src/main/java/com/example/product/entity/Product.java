package com.example.product.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;

}
