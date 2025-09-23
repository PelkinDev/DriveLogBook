package com.xomstudio.DriveLogBook;

import com.xomstudio.DriveLogBook.api.VehicleRepository;
import com.xomstudio.DriveLogBook.infrastructure.Fuel;
import com.xomstudio.DriveLogBook.infrastructure.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class VehicleConfig {

    @Bean
    CommandLineRunner commandLineRunner(VehicleRepository repository){
        return args -> {
            Vehicle v1 = new Vehicle("FRO-AA 2188", LocalDate.of(2019, Month.DECEMBER, 22), 63000, Fuel.ELECTRIC);
            Vehicle v2 = new Vehicle("BOO-OO 1234", LocalDate.of(2000, Month.JANUARY, 2), 463000, Fuel.DIESEL);
            Vehicle v3 = new Vehicle("MUU-QM 888",
                    LocalDate.of(2022, Month.OCTOBER, 12),
                    15,
                    "HONDA VIN",
                    "Honda",
                    "Rebel",
                    "black",
                    87,
                    Fuel.GASOLINE);

            repository.saveAll(List.of(v1, v2, v3));
        };
    };

}
