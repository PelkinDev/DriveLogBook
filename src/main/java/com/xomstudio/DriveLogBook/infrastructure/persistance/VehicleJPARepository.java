package com.xomstudio.DriveLogBook.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleJPARepository extends JpaRepository<VehicleEntity, Long> {

//        @Query("SELECT s FROM Vehicle s WHERE s.carLicensePlate = ?1")
    Optional<VehicleEntity> findVehicleByCarLicensePlate(String carLicensePlate);



}
