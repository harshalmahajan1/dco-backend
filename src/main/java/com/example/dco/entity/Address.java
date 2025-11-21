package com.example.dco.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer countryId;
    private Integer stateId;
    private Integer cityId;
    private String pinCode;
    @Column(length = 1000)
    private String address;
    private String type;

    @ManyToOne
    private Customer customer;
}
