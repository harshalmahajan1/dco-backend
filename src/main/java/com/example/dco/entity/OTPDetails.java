package com.example.dco.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;
    private Integer status; // 1 active, 0 inactive
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
