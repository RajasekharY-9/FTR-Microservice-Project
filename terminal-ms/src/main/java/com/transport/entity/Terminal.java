package com.transport.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="ftr_terminal")
public class Terminal {
    @Id
    private String terminalId;
    private String terminalName;
    private String country;
    private String itemType;
    private String terminalDescription;
    private Integer capacity;
    private Integer availableCapacity;
    private String status;
    private String harborLocation;



}
