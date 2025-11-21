package com.example.dco.service;

import com.example.dco.entity.Address;
import com.example.dco.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    public List<Address> saveAll(List<Address> list) {
        return repo.saveAll(list);
    }
}
