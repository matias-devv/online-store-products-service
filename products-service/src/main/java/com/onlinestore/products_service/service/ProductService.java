package com.onlinestore.products_service.service;

import com.onlinestore.products_service.dto.ProductDTO;
import com.onlinestore.products_service.model.Product;
import com.onlinestore.products_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public String createProduct(Product product) {
        iProductRepository.save(product);
        return "The product was successfully created";
    }

    @Override
    public String createAllProducts(List<Product> products) {
        iProductRepository.saveAll(products);
        return "The products were successfully created";
    }

    @Override
    public String updateProduct(Product product) {
        iProductRepository.save(product);
        return "The product was successfully updated";
    }

    @Override
    public String deleteProduct(Long id) {
        iProductRepository.deleteById(id);
        return "The product was successfully deleted";
    }

    @Override
    public ProductDTO findByProductId(Long id) {
        Product product = iProductRepository.findById(id).orElse(null);
        return this.convertProductToDTO(product);
    }

    private ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCode(product.getCode());
        productDTO.setName(product.getName());
        productDTO.setSingle_price(product.getSingle_price());
        return productDTO;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> products = iProductRepository.findAll();
        List<ProductDTO> productsDTOs = new ArrayList<>();
        for (Product product : products) {
            //the DTO that the method returns -> add it to the DTOs list
            productsDTOs.add( convertProductToDTO(product));
        }

        return productsDTOs;
    }

    @Override
    public List<ProductDTO> findProductsByCode(Long code) {
        List<Product> products = iProductRepository.findProductsByCode(code);
        List<ProductDTO> productsDTOs = new ArrayList<>();
        for (Product product : products) {
            //the DTO that the method returns -> add it to the DTOs list
            productsDTOs.add( convertProductToDTO(product));
        }

        return productsDTOs;
    }
}
