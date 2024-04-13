package com.example.springboot.repository;

import com.example.springboot.model.Cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    
    @Query("SELECT c FROM Cart c WHERE c.cartName = :cartName")
    List<Cart> findByCartName(@Param("cartName") String cartName);
}
