package com.transport.service;

import com.transport.dto.VehicleDTO;
import com.transport.entity.Vehicle;
import com.transport.exception.VehicleNotFoundException;
import com.transport.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {


    @Autowired
    private VehicleRepository vehicleRepository;

    public String insertNewVehicle(VehicleDTO vehicleDTO) throws ParseException {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        vehicleRepository.save(vehicle);
        return "Vehicle details are inserted successfully with vehicle number: " + vehicleDTO.getVehicleNumber();
    }

    public List<VehicleDTO> fetchAvailableVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.isEmpty()) {
            throw new VehicleNotFoundException("No vehicles found");
        }
        return vehicles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<String> availableHarborLocations(){
return vehicleRepository.findAllByHarborLocation();
    }

    public VehicleDTO fetchVehicleDetailsByVehicleNumber(String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with number: " + vehicleNumber));
        return convertToDTO(vehicle);
    }

    public List<VehicleDTO> fetchVehicleDetailsByVehicleName(String vehicleName) {
        List<Vehicle> vehicles = vehicleRepository.findByVehicleName(vehicleName);
        if (vehicles.isEmpty()) {
            throw new VehicleNotFoundException("No vehicles found with name: " + vehicleName);
        }
        return vehicles.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public String updateVehicleStatus(String vehicleNumber, String newStatus) {
        Vehicle vehicle = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with number: " + vehicleNumber));
        if (vehicle.getVehicleStatus().equals(newStatus)) {
            throw new RuntimeException("Vehicle status is already: " + newStatus);
        }
        vehicle.setVehicleStatus(newStatus);
        vehicleRepository.save(vehicle);
        return "Status of vehicle number " + vehicleNumber + " updated successfully to " + newStatus;
    }

    public String removeVehicle(String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findById(vehicleNumber)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with number: " + vehicleNumber));
        vehicleRepository.delete(vehicle);
        return "Vehicle removed successfully with number: " + vehicleNumber;
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setVehicleNumber(vehicle.getVehicleNumber());
        dto.setVehicleName(vehicle.getVehicleName());
        dto.setMaxLiftingCapacity(vehicle.getMaxLiftingCapacity());
        dto.setRetireDate(new SimpleDateFormat("dd-MMM-yyyy").format(vehicle.getRetireDate()));
        dto.setVehicleStatus(vehicle.getVehicleStatus());
        dto.setCountry(vehicle.getCountry());
        dto.setHarborLocation(vehicle.getHarborLocation());
        return dto;
    }

    private Vehicle convertToEntity(VehicleDTO dto) throws ParseException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setVehicleName(dto.getVehicleName());
        vehicle.setMaxLiftingCapacity(dto.getMaxLiftingCapacity());
        vehicle.setRetireDate(dto.getParsedRetireDate());
        vehicle.setVehicleStatus(dto.getVehicleStatus());
        vehicle.setCountry(dto.getCountry());
        vehicle.setHarborLocation(dto.getHarborLocation());
        return vehicle;
    }
}

