package com.transport.users_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;
    private String password;
    private String nationality;
    private String passportNumber;
    private String permanentAddress;
    private String officeAddress;
    private Long personalIdentificationNumber;

    // Getters and Setters
}
