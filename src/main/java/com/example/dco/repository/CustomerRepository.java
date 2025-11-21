package com.example.dco.repository;

import com.example.dco.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String phone);
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPanCard(String panCard);
    boolean existsByPhoneOrEmailOrPanCard(String phone, String email, String panCard);
}
