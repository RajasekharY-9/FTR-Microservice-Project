package com.transport.workitem_ms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class WorkitemDTO {

    @Size(max = 20, message = "{workitem.workitemId.invalid}")
    private String workitemId;

    @NotNull(message = "{workitem.userId.must}")
    @Min(value = 1, message = "{workitem.userId.invalid}")
    @Max(value = 99999, message = "{workitem.userId.invalid}")
    private Long userId;

    @NotBlank(message = "{workitem.itemName.must}")
    @Size(min = 3, max = 25, message = "{workitem.itemName.invalid}")
    private String itemName;

    @NotBlank(message = "{workitem.itemType.must}")
    @Size(min = 4, max = 25, message = "{workitem.itemType.invalid}")
    private String itemType;

    @NotBlank(message = "{workitem.itemDescription.must}")
    @Size(min = 10, max = 45, message = "{workitem.itemDescription.invalid}")
    private String itemDescription;

    @NotBlank(message = "{workitem.messageToRecipient.must}")
    @Size(min = 10, max = 50, message = "{workitem.messageToRecipient.invalid}")
    private String messageToRecipient;

    @NotBlank(message = "{workitem.quantity.must}")
    private String quantity;

    @NotBlank(message = "{workitem.sourceCountry.must}")
    @Size(min = 5, max = 25, message = "{workitem.sourceCountry.invalid}")
    private String sourceCountry;

    @NotBlank(message = "{workitem.destinationCountry.must}")
    @Size(min = 5, max = 25, message = "{workitem.destinationCountry.invalid}")
    private String destinationCountry;

    @NotBlank(message = "{workitem.originHarborLocation.must}")
    @Size(min = 5, max = 25, message = "{workitem.originHarborLocation.invalid}")
    private String originHarborLocation;

    @NotBlank(message = "{workitem.selectedHarborLocation.must}")
    @Size(min = 5, max = 25, message = "{workitem.selectedHarborLocation.invalid}")
    private String selectedHarborLocation;

    @NotNull(message = "{workitem.shippingDate.must}")
    @Future(message = "{workitem.shippingDate.invalid}")
    private Date shippingDate;

    @NotNull(message = "{workitem.amount.must}")
    @Min(value = 0, message = "{workitem.amount.invalid}")
    private Long amount;


}
