package com.transport.workitem_ms.repository;

import com.transport.workitem_ms.entity.Workitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface WorkitemRepository extends JpaRepository<Workitem,String> {
    List<Workitem> findByUserId(Long userId);

   // List<String> findAvailableHarborLocations(String fromCountry);

  //  Optional<Workitem> findByVehicleNumber(String vehicleNumber);
}
