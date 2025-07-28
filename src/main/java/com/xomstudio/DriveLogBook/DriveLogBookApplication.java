package com.xomstudio.DriveLogBook;

import com.xomstudio.DriveLogBook.api.Fuel;
import com.xomstudio.DriveLogBook.domain.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.*;

@SpringBootApplication
public class DriveLogBookApplication {

	public static void main(String[] args) {

		Vehicle v1 = new Vehicle("FR-XM 188", LocalDate.of(2019, Month.DECEMBER, 22), 63000, Fuel.GASOLINE);
		Vehicle v2 = new Vehicle("FR-OO 1234", LocalDate.of(2000, Month.JANUARY, 2), 463000, Fuel.DIESEL);

		System.out.println(v1.toString());
		System.out.println(v2.toString());


		SpringApplication.run(DriveLogBookApplication.class, args);
	}



}
