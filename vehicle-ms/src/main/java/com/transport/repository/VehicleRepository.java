package com.transport.repository;

import com.transport.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
    List<Vehicle> findByVehicleName(String vehicleName);
    List<Vehicle> findByVehicleStatus(String vehicleStatus);
}
