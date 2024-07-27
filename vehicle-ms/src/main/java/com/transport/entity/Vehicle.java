package com.transport.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="ftr_vehicle")
public class Vehicle {
    @Id
    private String vehicleNumber;

    private String vehicleName;
    private Double maxLiftingCapacity;
    private Date retireDate;
    private String vehicleStatus;
    private String country;
    private String harborLocation;

}
