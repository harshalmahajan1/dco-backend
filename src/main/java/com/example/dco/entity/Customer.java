package com.example.dco.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String panCard;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OTPDetails> otps;
}
