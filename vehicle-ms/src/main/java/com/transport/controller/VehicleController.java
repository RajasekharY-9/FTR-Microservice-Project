package com.transport.controller;


import com.transport.dto.VehicleDTO;
import com.transport.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {


    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<String> insertNewVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) throws ParseException {
        String response = vehicleService.insertNewVehicle(vehicleDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> fetchAvailableVehicles() {
        List<VehicleDTO> vehicles = vehicleService.fetchAvailableVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/managed-number/{vehicleNumber}")
    public ResponseEntity<VehicleDTO> fetchVehicleDetailsByVehicleNumber(@PathVariable String vehicleNumber) {
        VehicleDTO vehicle = vehicleService.fetchVehicleDetailsByVehicleNumber(vehicleNumber);
        return ResponseEntity.ok(vehicle);
    }
    @GetMapping("/harbor-all")
    //http://localhost:7002/vehicles/harbor-all
    public List<String> availableHarborLocations(){
       return vehicleService.availableHarborLocations();
    }
    @GetMapping("/managed-name/{vehicleName}")
    public ResponseEntity<List<VehicleDTO>> fetchVehicleDetailsByVehicleName(@PathVariable String vehicleName) {
        List<VehicleDTO> vehicles = vehicleService.fetchVehicleDetailsByVehicleName(vehicleName);
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/{vehicleNumber}")
    public ResponseEntity<String> updateVehicleStatus(@PathVariable String vehicleNumber, @RequestBody String newStatus) {
        String response = vehicleService.updateVehicleStatus(vehicleNumber, newStatus);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{vehicleNumber}")
    public ResponseEntity<String> removeVehicle(@PathVariable String vehicleNumber) {
        String response = vehicleService.removeVehicle(vehicleNumber);
        return ResponseEntity.ok(response);
    }
}
