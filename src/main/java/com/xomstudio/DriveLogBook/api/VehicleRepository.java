package com.xomstudio.DriveLogBook.api;

import com.xomstudio.DriveLogBook.infrastructure.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    //    @Query("SELECT s FROM Vehilcle s WHERE s.carLicensePlate = ?1")
    Optional<Vehicle> findVehicleByCarLicensePlate(String carLicensePlate);



}
