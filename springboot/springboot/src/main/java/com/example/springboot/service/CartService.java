package com.example.springboot.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springboot.model.Cart;
import com.example.springboot.repository.CartRepo;

@Service
public class CartService {

    @Autowired
    CartRepo bookingRepository;

    public Cart create(Cart booking) {
        return bookingRepository.save(booking);
    }

    public List<Cart> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Cart> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public boolean updateBooking(int id, Cart booking) {
        if (!bookingRepository.existsById(id)) {
            return false;
        }
        booking.setId(id);
        bookingRepository.save(booking);
        return true;
    }

    public boolean deleteBooking(int id) {
        if (!bookingRepository.existsById(id)) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }

    public List<Cart> getAllBookingsSortedBy(String sortBy) {
        return bookingRepository.findAll(Sort.by(sortBy));
    }

    public Page<Cart> getAllBookingsPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookingRepository.findAll(pageable);
    }

    public boolean updateCart(int id, Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCart'");
    }

    public boolean deleteCart(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCart'");
    }
}
