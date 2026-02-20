package com.xomstudio.DriveLogBook.infrastructure;


import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.domain.Fuel;
import com.xomstudio.DriveLogBook.infrastructure.entity.VehicleEntity;
import com.xomstudio.DriveLogBook.infrastructure.exceptions.VehicleValidateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VehicleValidationsTests {

    private VehicleRepository underTest;

    @Autowired
    public VehicleValidationsTests(VehicleRepository underTest) {
        this.underTest = underTest;
    }


    @Test
    public void testValidateVehicle(){
        VehicleValidation.valideVehicle(testVehicleA);
    }

    @Test
    public void testValidateCarPlateNotNull(){
        testVehicleA.setCarLicensePlate(null);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Car plate must be defienied").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateCarPlateNotEmpty(){
        testVehicleA.setCarLicensePlate("");
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Car plate must be defienied").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateFirstRegistrationNotNull(){
        testVehicleA.setFirstRegistration(null);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Registration can not be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateFirstRegistrationNotInFuture(){
        testVehicleA.setFirstRegistration(LocalDate.of(2099, Month.DECEMBER, 31));
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Registration can not be in the future").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateMileage(){
        testVehicleA.setMileage(-100);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Mileage must be bigger or equals than 0").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateVinNotNull(){
        testVehicleA.setVin(null);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("VIN number cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateVinNotEmpty(){
        testVehicleA.setVin("");
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("VIN number cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateCarBrandNotNull(){
        testVehicleA.setCarBrand(null);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Car brand cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateCarBrandNotEmpty(){
        testVehicleA.setCarBrand("");
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Car brand cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateCarModelNotNull(){
        testVehicleA.setCarModel(null);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Car model cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateCarModelNotEmpty(){
        testVehicleA.setCarModel("");
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Car model cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    public void testValidateCarColorNotNull(){
        testVehicleA.setCarColor(null);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Color cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateCarColorNotEmpty(){
        testVehicleA.setCarColor("");
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Color cannot be null or empty").isEqualTo(e.getMessage());
        }
    }

    @Test
    public void testValidateEnginePower(){
        testVehicleA.setEnginePower(0);
        try {
            VehicleValidation.valideVehicle(testVehicleA);
            fail();
        } catch (VehicleValidateException e){
            assertThat("Engine power must be bigger than 0").isEqualTo(e.getMessage());
        }
    }


    private VehicleEntity testVehicleA = VehicleEntity.builder()
            .id(1L)
            .carLicensePlate("Z-ZZ 111")
            .firstRegistration(LocalDate.of(2015, Month.JUNE, 12))
            .mileage(65536)
            .vin("Jaguar VIN")
            .carBrand("Jaguar")
            .carModel("X-Type")
            .carColor("Green")
            .enginePower(299)
            .petrol(Fuel.GASOLINE)
            .build();

}
