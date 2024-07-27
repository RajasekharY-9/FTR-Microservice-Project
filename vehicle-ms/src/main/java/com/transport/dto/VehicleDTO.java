package com.transport.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class VehicleDTO {
    @NotBlank(message = "{vehicle.vehicleNumber.must}")
    @Pattern(regexp = "^[A-Z]{2}\\d{4}$", message = "{vehicle.vehicleNumber.invalid}")
    private String vehicleNumber;

    @NotBlank(message = "{vehicle.vehicleName.must}")
    @Size(max = 30, message = "{vehicle.vehicleName.invalid}")
    @Pattern(regexp = "Tower crane|FirePlace Crane|Double treadwheel Crane|Crawler Crane|Aerial Crane|Deck Crane", message = "{vehicle.vehicleName.invalid}")
    private String vehicleName;

    @NotNull(message = "{vehicle.maxLiftingCapacity.must}")
    @Digits(integer = 5, fraction = 1, message = "{vehicle.maxLiftingCapacity.invalid}")
    private Double maxLiftingCapacity;

    @NotBlank(message = "{vehicle.retireDate.must}")
    private String retireDate;

    @NotBlank(message = "{vehicle.vehicleStatus.must}")
    @Pattern(regexp = "Active|Retired|InProgress", message = "{vehicle.vehicleStatus.invalid}")
    private String vehicleStatus;

    @NotBlank(message = "{vehicle.country.must}")
    @Size(min = 5, max = 25, message = "{vehicle.country.invalid}")
    private String country;

    @NotBlank(message = "{vehicle.harborLocation.must}")
    @Size(min = 5, max = 25, message = "{vehicle.harborLocation.invalid}")
    private String harborLocation;


    public Date getParsedRetireDate() throws ParseException {
        return new SimpleDateFormat("dd-MMM-yyyy").parse(this.retireDate);
    }
}
