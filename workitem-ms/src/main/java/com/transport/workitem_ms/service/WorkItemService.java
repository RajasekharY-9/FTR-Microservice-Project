package com.transport.workitem_ms.service;

import com.transport.workitem_ms.dto.WorkitemDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface WorkItemService {
    public WorkitemDTO createWorkitem(@Valid WorkitemDTO workitemDto);
    public void updateWorkitemId(String workitemId, @Valid WorkitemDTO workitemDto);
   // public List<String> fetchAvailableHarborLocations(String fromCountry);
    public List<WorkitemDTO> fetchWorkItemDetails();
    public List<WorkitemDTO> trackWorkitemByUser(Long userId);
    public WorkitemDTO fetchWorkItemStatus(String workitemId);
    public void updateWorkItemStatus(String workitemId);
    public void assignTerminalForWorkitem(String workitemId);
   // public WorkitemDTO fetchWorkItemDetailsByVehicleNumber(String vehicleNumber);
    public void allocateVehicle(String workitemId);

}
