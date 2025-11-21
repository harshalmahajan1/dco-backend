package com.example.dco.controller;

import com.example.dco.entity.Address;
import com.example.dco.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/country")
    public ResponseEntity<List<String>> countries() {
        return ResponseEntity.ok(List.of("India", "USA"));
    }

    @GetMapping("/state/{countryId}")
    public ResponseEntity<List<String>> states(@PathVariable Integer countryId) {
        return ResponseEntity.ok(List.of("Maharashtra", "Karnataka"));
    }

    @GetMapping("/city/{stateId}")
    public ResponseEntity<List<String>> cities(@PathVariable Integer stateId) {
        return ResponseEntity.ok(List.of("Pune", "Mumbai"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAddresses(@RequestBody List<Address> addresses) {
        addressService.saveAll(addresses);
        return ResponseEntity.ok("Addresses saved");
    }
}
