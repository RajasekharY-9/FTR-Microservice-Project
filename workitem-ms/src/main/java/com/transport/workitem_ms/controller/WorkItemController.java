package com.transport.workitem_ms.controller;

import com.transport.workitem_ms.dto.WorkitemDTO;
import com.transport.workitem_ms.service.WorkItemService;
import com.transport.workitem_ms.service.WorkitemServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workItems")
public class WorkItemController {
    @Autowired
    private WorkItemService workitemService;

    @PostMapping
    //http://localhost:7003/ftr/workItems
    public ResponseEntity<WorkitemDTO> createWorkitem(@Valid @RequestBody WorkitemDTO newWorkitem) {
        WorkitemDTO createdWorkitem = workitemService.createWorkitem(newWorkitem);
        return ResponseEntity.ok(createdWorkitem);
    }

    @PutMapping("/{workitemId}")
    //http://localhost:7003/ftr/workItems/J71948
    public ResponseEntity<String> updateWorkItem(@PathVariable String workitemId, @Valid @RequestBody WorkitemDTO workitemDTO) {
        workitemService.updateWorkitemId(workitemId, workitemDTO);
        return ResponseEntity.ok("Workitem updated successfully.");
    }

 /*   @GetMapping("/{fromCountry}")
    public ResponseEntity<List<String>> fetchAvailableHarborLocations(@PathVariable String fromCountry) {
        List<String> harborLocations = workitemService.fetchAvailableHarborLocations(fromCountry);
        return ResponseEntity.ok(harborLocations);
    }*/

    @GetMapping
    public ResponseEntity<List<WorkitemDTO>> fetchWorkItemDetails() {
        List<WorkitemDTO> workitems = workitemService.fetchWorkItemDetails();
        return ResponseEntity.ok(workitems);
    }

    @GetMapping("/managed-user/{userId}")
    public ResponseEntity<List<WorkitemDTO>> trackWorkitemByUser(@PathVariable Long userId) {
        List<WorkitemDTO> workitems = workitemService.trackWorkitemByUser(userId);
        return ResponseEntity.ok(workitems);
    }

    @GetMapping("/managed-status/{workitemId}")
    public ResponseEntity<WorkitemDTO> fetchWorkItemStatus(@PathVariable String workitemId) {
        WorkitemDTO workitem = workitemService.fetchWorkItemStatus(workitemId);
        return ResponseEntity.ok(workitem);
    }

    @PutMapping("/managed-update/{workitemId}")
    public ResponseEntity<String> updateWorkItemStatus(@PathVariable String workitemId) {
        workitemService.updateWorkItemStatus(workitemId);
        return ResponseEntity.ok("Workitem status updated successfully.");
    }

    @PostMapping("/managed-terminal/{workitemId}")
    public ResponseEntity<String> assignTerminalForWorkitem(@PathVariable String workitemId) {
        workitemService.assignTerminalForWorkitem(workitemId);
        return ResponseEntity.ok("Terminal assigned successfully.");
    }

  /*  @GetMapping("/managed-vehicle/{vehicleNumber}")
    public ResponseEntity<WorkitemDTO> fetchWorkItemDetailsByVehicleNumber(@PathVariable String vehicleNumber) {
        WorkitemDTO workitem = workitemService.fetchWorkItemDetailsByVehicleNumber(vehicleNumber);
        return ResponseEntity.ok(workitem);
    }*/

    @PostMapping("/managed-vehicle/{workitemId}")
    public ResponseEntity<String> allocateVehicle(@PathVariable String workitemId) {
        workitemService.allocateVehicle(workitemId);
        return ResponseEntity.ok("Vehicle allocated successfully.");
    }

}
