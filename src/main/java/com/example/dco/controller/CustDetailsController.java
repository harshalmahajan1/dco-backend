package com.example.dco.controller;

import com.example.dco.dto.CustomerDetailsInfo;
import com.example.dco.entity.Customer;
import com.example.dco.service.CustomerService;
import com.example.dco.service.OTPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cust-details")
public class CustDetailsController {

    private final CustomerService customerService;
    private final OTPService otpService;

    public CustDetailsController(CustomerService customerService, OTPService otpService) {
        this.customerService = customerService;
        this.otpService = otpService;
    }

    @PostMapping("/save-cust")
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerDetailsInfo info) {
        if (customerService.existsByIdentifiers(info.getMobileNumber(), info.getEmail(), info.getPanCard())) {
            return ResponseEntity.status(409).body("Customer already exists");
        }

        Customer customer = new Customer();
        customer.setFirstName(info.getFirstName());
        customer.setMiddleName(info.getMiddleName());
        customer.setLastName(info.getLastName());
        customer.setPhone(info.getMobileNumber());
        customer.setEmail(info.getEmail());
        customer.setPanCard(info.getPanCard());
        Customer saved = customerService.save(customer);

        otpService.generateAndSendOTP(saved);

        return ResponseEntity.ok(saved);
    }
}
