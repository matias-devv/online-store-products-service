package com.onlinestore.products_service.controller;

import com.onlinestore.products_service.dto.ProductDTO;
import com.onlinestore.products_service.model.Product;
import com.onlinestore.products_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private IProductService iProductService;

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product){
        return iProductService.createProduct(product);
    }

    @PostMapping("/create-all")
    public String createProducts(@RequestBody List<Product> products){
        return iProductService.createAllProducts(products);
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product){
        return iProductService.updateProduct(product);
    }

    @DeleteMapping("/delete/{code}")
    public String deleteProduct(@PathVariable Long code){
        return iProductService.deleteProduct(code);
    }

    @GetMapping("/find/{code}")
    public ProductDTO findProductByCode(@PathVariable Long code){
        return iProductService.findByProductId(code);
    }

    @GetMapping("/find-all")
    public List<ProductDTO> findAllProducts(){
        return iProductService.findAllProducts();
    }

    @GetMapping("/find")
    public List<ProductDTO> findProductsByCodes(@RequestBody List<Long> codes){
        return iProductService.findProductsByCodes(codes);
    }
}
