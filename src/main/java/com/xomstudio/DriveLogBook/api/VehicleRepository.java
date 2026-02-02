package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

//        @Query("SELECT s FROM Vehicle s WHERE s.carLicensePlate = ?1")
    Optional<VehicleEntity> findVehicleByCarLicensePlate(String carLicensePlate);



}
