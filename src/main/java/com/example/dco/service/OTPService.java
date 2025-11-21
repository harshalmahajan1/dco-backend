package com.example.dco.service;

import com.example.dco.entity.Customer;
import com.example.dco.entity.OTPDetails;
import com.example.dco.repository.OTPDetailsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class OTPService {

    private final OTPDetailsRepository otpRepo;

    @Value("${twilio.accountSid:}")
    private String accountSid;

    @Value("${twilio.authToken:}")
    private String authToken;

    @Value("${twilio.fromNumber:}")
    private String fromNumber;

    public OTPService(OTPDetailsRepository otpRepo) {
        this.otpRepo = otpRepo;
    }

    public OTPDetails generateAndSendOTP(Customer customer) {
        String otp = generateOTP();
        OTPDetails otpDetails = new OTPDetails();
        otpDetails.setOtp(otp);
        otpDetails.setStatus(1);
        otpDetails.setCreatedAt(Instant.now());
        otpDetails.setCustomer(customer);
        otpRepo.save(otpDetails);

        return otpDetails;
    }

    public boolean validateOtp(Long customerId, String otp) {
        return otpRepo.findFirstByCustomerIdAndOtpAndStatus(customerId, otp, 1).isPresent();
    }

    public void deactivateOtp(Long id) {
        otpRepo.findById(id).ifPresent(o -> {
            o.setStatus(0);
            otpRepo.save(o);
        });
    }

    private String generateOTP() {
        Random rnd = new Random();
        int number = rnd.nextInt(900000) + 100000; // 6-digit
        return String.valueOf(number);
    }
}
