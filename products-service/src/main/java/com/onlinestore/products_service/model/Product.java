package com.onlinestore.products_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Schema(description = "Product entity")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated product code", example = "12345", accessMode = Schema.AccessMode.READ_ONLY)
    private Long code;

    @Schema(description = "Product name", example = "Laptop Dell XPS 15", required = true)
    private String name;

    @Schema(description = "Product brand", example = "Dell", required = true)
    private String brand;

    @Schema(description = "Unit price of the product", example = "1299.99", required = true)
    private BigDecimal single_price;
}
