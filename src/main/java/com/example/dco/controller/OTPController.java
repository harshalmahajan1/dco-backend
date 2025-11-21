package com.example.dco.controller;

import com.example.dco.service.OTPService;
import com.example.dco.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class OTPController {
    private final OTPService otpService;
    private final CustomerService customerService;

    public OTPController(OTPService otpService, CustomerService customerService) {
        this.otpService = otpService;
        this.customerService = customerService;
    }

    @GetMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestParam Long customerId, @RequestParam String otp) {
        boolean ok = otpService.validateOtp(customerId, otp);
        if (ok) {
            return ResponseEntity.ok("Mobile OTP verified. Email OTP sending should follow.");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP");
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestParam Long customerId) {
        var cust = customerService.findById(customerId);
        if (cust.isEmpty()) return ResponseEntity.badRequest().body("Customer not found");
        otpService.generateAndSendOTP(cust.get());
        return ResponseEntity.ok("OTP resent");
    }
}
