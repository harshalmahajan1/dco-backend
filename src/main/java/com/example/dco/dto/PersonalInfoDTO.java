package com.example.dco.dto;

import lombok.Data;

@Data
public class PersonalInfoDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String dob;
    private String phone;
    private String email;
    private String panCard;
}
