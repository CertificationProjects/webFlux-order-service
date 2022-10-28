package com.orderservice.orderservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Product {
    @Id
    private String id;
    private String description;
    private Double price;
}
