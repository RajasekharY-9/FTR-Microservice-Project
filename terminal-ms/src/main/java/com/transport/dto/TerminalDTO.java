package com.transport.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TerminalDTO {

    private String terminalId;

    @NotBlank(message = "{terminal.terminalName.must}")
    @Size(min = 3, max = 20, message = "{terminal.terminalName.invalid}")
    private String terminalName;

    @NotBlank(message = "{terminal.country.must}")
    @Size(min = 3, max = 20, message = "{terminal.country.invalid}")
    private String country;

    @NotBlank(message = "{terminal.itemType.must}")
    @Size(min = 4, max = 30, message = "{terminal.itemType.invalid}")
    private String itemType;

    @NotBlank(message = "{terminal.terminalDescription.must}")
    @Pattern(regexp = "^[a-zA-Z0-9-]{1,25}$", message = "{terminal.terminalDescription.invalid}")
    private String terminalDescription;

    @NotNull(message = "{terminal.capacity.must}")
    @Max(value = 99999, message = "{terminal.capacity.invalid}")
    private Integer capacity;

    @NotNull(message = "{terminal.capacity.must}")
    @Max(value = 99999, message = "{terminal.capacity.invalid}")
    private Integer availableCapacity;

    @NotBlank(message = "{terminal.status.must}")
    @Pattern(regexp = "Available|Not Available", message = "{terminal.status.invalid}")
    private String status;

    @NotBlank(message = "{terminal.harborLocation.must}")
    @Size(min = 5, max = 25, message = "{terminal.harborLocation.invalid}")
    private String harborLocation;
}
