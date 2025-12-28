package com.onlinestore.products_service.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {

    private Long code;
    private String name;
    private String brand;
    private BigDecimal single_price;
}
