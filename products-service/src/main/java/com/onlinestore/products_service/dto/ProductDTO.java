package com.onlinestore.products_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Product information")
public class ProductDTO {

    @Schema(description = "Unique product code", example = "12345")
    private Long code;

    @Schema(description = "Product name", example = "Laptop Dell XPS 15")
    private String name;

    @Schema(description = "Unit price of the product", example = "1299.99")
    private BigDecimal single_price;
}
