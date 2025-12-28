package com.onlinestore.products_service.controller;

import com.onlinestore.products_service.dto.ProductDTO;
import com.onlinestore.products_service.model.Product;
import com.onlinestore.products_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{

    @Autowired
    private IProductService iProductService;

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product){
        return iProductService.createProduct(product);
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product){
        return iProductService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        return iProductService.deleteProduct(id);
    }

    @GetMapping("/find/{id}")
    public ProductDTO findByProductId(Long id){
        return iProductService.findByProductId(id);
    }

    @GetMapping("/find-all")
    public List<ProductDTO> findAllProducts(){
        return iProductService.findAllProducts();
    }
}
