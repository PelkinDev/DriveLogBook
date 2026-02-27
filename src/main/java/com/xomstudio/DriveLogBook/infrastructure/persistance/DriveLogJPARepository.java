package com.xomstudio.DriveLogBook.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriveLogJPARepository extends JpaRepository<DriveLogEntity, Long>{

    @NativeQuery(value = "SELECT * FROM drive_logs WHERE vehicle_id = ?1")
    List<DriveLogEntity> findAllByVehicleId(Long vehicleId);
}
