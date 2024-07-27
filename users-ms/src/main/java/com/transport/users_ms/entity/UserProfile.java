package com.transport.users_ms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ftr_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
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
