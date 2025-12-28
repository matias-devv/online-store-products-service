package com.onlinestore.products_service.repository;

import com.onlinestore.products_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Producto p WHERE p.code = :codeProduct")
    public List<Product> findProductsByCode(@Param("codeProduct") Long codeProduct);
}
