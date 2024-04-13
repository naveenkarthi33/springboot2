package com.example.springboot.repository;

import com.example.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT b FROM product b WHERE b.productName = ?1")
    List<Product> findByBusName(String busName);
    
    @Query("SELECT b FROM Bus b WHERE b.id IN (SELECT DISTINCT b.id FROM Bus b INNER JOIN b.users u WHERE u.id = ?1)")
    List<Product> findByUserId(int userId);
}
