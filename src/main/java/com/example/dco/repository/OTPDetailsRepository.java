package com.example.dco.repository;

import com.example.dco.entity.OTPDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPDetailsRepository extends JpaRepository<OTPDetails, Long> {
    Optional<OTPDetails> findFirstByCustomerIdAndOtpAndStatus(Long customerId, String otp, Integer status);
}
