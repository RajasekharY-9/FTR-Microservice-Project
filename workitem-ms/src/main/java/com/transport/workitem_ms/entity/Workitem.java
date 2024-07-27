package com.transport.workitem_ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="ftr_workitems")
public class Workitem {
    @Id

    private String workitemId;

    private Long userId;
    private String itemName;
    private String itemType;
    private String itemDescription;
    private String messageToRecipient;
    private String quantity;


    private String sourceCountry;


    private String destinationCountry;


    private String originHarborLocation;


    private String selectedHarborLocation;

    private Date shippingDate;

    private Long amount;
}
