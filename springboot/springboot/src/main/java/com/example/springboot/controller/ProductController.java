package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.Product;
import com.example.springboot.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class ProductController {

    @Autowired
    private ProductService busService;

    @PostMapping
    public ResponseEntity<Product> addBus(@RequestBody Product bus) {
        Product newBus = busService.create(bus);
        return new ResponseEntity<>(newBus, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllBuses() {
        List<Product> buses = busService.getAllBuses();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getBusById(@PathVariable("id") int id) {
        Product bus = busService.getBusById(id).orElse(null);
        if (bus != null) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateBus(@PathVariable("id") int id, @RequestBody Product bus) {
        if (busService.updateBus(id, bus)) {
            return new ResponseEntity<>(bus, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBus(@PathVariable("id") int id) {
        if (busService.deleteBus(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> getAllBusesSorted(@RequestParam String sortBy) {
        List<Product> buses = busService.getAllBusesSortedBy(sortBy);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Product>> getAllBusesPaginated(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<Product> busesPage = busService.getAllBusesPaginated(pageNo, pageSize);
        return new ResponseEntity<>(busesPage, HttpStatus.OK);
    }
    @GetMapping("/name/{busName}")
    public ResponseEntity<List<Product>> getBusesByBusName(@PathVariable String busName) {
        List<Product> buses = busService.findByBusName(busName);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getBusesByUserId(@PathVariable int userId) {
        List<Product> buses = busService.findByUserId(userId);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }
}
