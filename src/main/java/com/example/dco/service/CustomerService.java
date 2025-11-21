package com.example.dco.service;

import com.example.dco.entity.Customer;
import com.example.dco.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean existsByIdentifiers(String phone, String email, String pan) {
        return customerRepository.existsByPhoneOrEmailOrPanCard(phone, email, pan);
    }

    public Customer save(Customer c) {
        return customerRepository.save(c);
    }

    public Optional<Customer> findByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
