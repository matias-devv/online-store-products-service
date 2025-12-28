package com.onlinestore.products_service.service;

import com.onlinestore.products_service.dto.ProductDTO;
import com.onlinestore.products_service.model.Product;

import java.util.List;

public interface IProductService {

    public String createProduct(Product product);

    public String createAllProducts(List<Product> products);

    public String updateProduct(Product product);

    public String deleteProduct(Long id);

    public ProductDTO findByProductId(Long id);

    public List<ProductDTO> findAllProducts();

    public List<ProductDTO> findProductsByCode(List<Long> codes);

}
