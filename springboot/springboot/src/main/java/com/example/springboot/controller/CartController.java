package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.Cart;
import com.example.springboot.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        Cart newCart = cartService.create(cart);
        return new ResponseEntity<>(newCart, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllBookings();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") int id) {
        Cart cart = cartService.getBookingById(id).orElse(null);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") int id, @RequestBody Cart cart) {
        if (cartService.updateCart(id, cart)) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCart(@PathVariable("id") int id) {
        if (cartService.deleteCart(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Cart>> getAllCartsSorted(@RequestParam String sortBy) {
        List<Cart> carts = cartService.getAllBookingsSortedBy(sortBy);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Cart>> getAllBookingsPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Cart> cartsPage = cartService.getAllBookingsPaginated(pageNo, pageSize);
        return new ResponseEntity<>(cartsPage, HttpStatus.OK);
    }
}
