package com.onlinestore.products_service.controller;

import com.onlinestore.products_service.dto.ProductDTO;
import com.onlinestore.products_service.model.Product;
import com.onlinestore.products_service.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "API for product catalog management")
public class ProductController{

    @Autowired
    private IProductService iProductService;

    @PostMapping("/create")
    @Operation(summary = "Create a new product")
    @ApiResponse(
            responseCode = "200",
            description = "Product created successfully",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
    )
    public String createProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product data to create",
                    required = true
            )
            @RequestBody Product product
    ){
        return iProductService.createProduct(product);
    }

    @PostMapping("/create-all")
    @Operation(
            summary = "Create multiple products",
            description = "Batch creation of products for catalog initialization or bulk imports"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Products created successfully",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
    )
    public String createProducts(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "List of products to create",
                    required = true
            )
            @RequestBody List<Product> products
    ){
        return iProductService.createAllProducts(products);
    }

    @PutMapping("/update")
    @Operation(summary = "Update product information")
    @ApiResponse(
            responseCode = "200",
            description = "Product updated successfully",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
    )
    public String updateProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product data to update. Must include product code.",
                    required = true
            )
            @RequestBody Product product
    ){
        return iProductService.updateProduct(product);
    }

    @DeleteMapping("/delete/{code}")
    @Operation(summary = "Delete a product")
    @ApiResponse(
            responseCode = "200",
            description = "Product deleted successfully",
            content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
    )
    public String deleteProduct(
            @Parameter(description = "Product code to delete", required = true, example = "12345")
            @PathVariable Long code
    ){
        return iProductService.deleteProduct(code);
    }

    @PostMapping("/find/{code}")
    @Operation(summary = "Find product by code")
    @ApiResponse(
            responseCode = "200",
            description = "Product found",
            content = @Content(schema = @Schema(implementation = ProductDTO.class))
    )
    public ProductDTO findProductByCode(
            @Parameter(description = "Product code", required = true, example = "12345")
            @PathVariable Long code
    ){
        return iProductService.findByProductId(code);
    }

    @GetMapping("/find-all")
    @Operation(summary = "Get all products")
    @ApiResponse(
            responseCode = "200",
            description = "List of all products",
            content = @Content(schema = @Schema(implementation = ProductDTO.class, type = "array"))
    )
    public List<ProductDTO> findAllProducts(){
        return iProductService.findAllProducts();
    }

    @PostMapping("/find/by-codes")
    @Operation(
            summary = "Find products by multiple codes",
            description = "Retrieves multiple products by their codes. Used by Shopping Cart Service for product validation."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Products found",
            content = @Content(schema = @Schema(implementation = ProductDTO.class, type = "array"))
    )
    public List<ProductDTO> findProductsByCodes(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "List of product codes to retrieve",
                    required = true,
                    content = @Content(
                            schema = @Schema(type = "array", implementation = Long.class),
                            examples = @ExampleObject(
                                    name = "Find multiple products",
                                    value = "[12345, 67890, 11223]"
                            )
                    )
            )
            @RequestBody List<Long> codes
    ){
        return iProductService.findProductsByCodes(codes);
    }
}
