package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Product;
import com.example.springboot.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo busRepository;

    public Product create(Product bus) {
        return busRepository.save(bus);
    }

    public List<Product> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Product> getBusById(int id) {
        return busRepository.findById(id);
    }

    public boolean updateBus(int id, Product bus) {
        if (!busRepository.existsById(id)) {
            return false;
        }
        bus.setId(id);
        busRepository.save(bus);
        return true;
    }

    public boolean deleteBus(int id) {
        if (!busRepository.existsById(id)) {
            return false;
        }
        busRepository.deleteById(id);
        return true;
    }

    public List<Product> getAllBusesSortedBy(String sortBy) {
        return busRepository.findAll(Sort.by(sortBy));
    }

    public Page<Product> getAllBusesPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return busRepository.findAll(pageable);
    }
    public List<Product> findByBusName(String busName) {
        return busRepository.findByBusName(busName);
    }

    public List<Product> findByUserId(int userId) {
        return busRepository.findByUserId(userId);
    }

}
