package com.xomstudio.DriveLogBook.domain;

import com.xomstudio.DriveLogBook.api.Fuel;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table
public class Vehicle {
    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String carLicensePlate;

    private LocalDate firstRegistration;

    private int mileage;

    private String vin;

    private String carBrand;

    private String carModel;

    private String carColor;

    private int enginePower;

    private Fuel petrol;


    public Vehicle(){
    }

    public Vehicle(String carLicensePlate, LocalDate firstRegistration, int mileage, String vin, String carBrand, String carModel, String carColor, int enginePower, Fuel petrol) {
        this.carLicensePlate = carLicensePlate;
        this.firstRegistration = firstRegistration;
        this.mileage = mileage;
        this.vin = vin;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carColor = carColor;
        this.enginePower = enginePower;
        this.petrol = petrol;
    }

    public Vehicle(String carLicensePlate, LocalDate firstRegistration, int mileage, Fuel petrol) {
        this.carLicensePlate = carLicensePlate;
        this.firstRegistration = firstRegistration;
        this.mileage = mileage;
        this.petrol = petrol;

        this.vin = "no vin";
        this.carBrand = "no brand";
        this.carModel = "no model";
        this.carColor = "no color";
        this.enginePower = -1;
    }


    public Long getId() {
        return id;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public LocalDate getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(LocalDate firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public Fuel getPetrol() {
        return petrol;
    }

    public void setPetrol(Fuel petrol) {
        this.petrol = petrol;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", carLicensePlate='" + carLicensePlate + '\'' +
                ", firstRegistration=" + firstRegistration +
                ", mileage=" + mileage +
                ", vin='" + vin + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carColor='" + carColor + '\'' +
                ", enginePower=" + enginePower +
                ", petrol=" + petrol +
                '}';
    }
}
