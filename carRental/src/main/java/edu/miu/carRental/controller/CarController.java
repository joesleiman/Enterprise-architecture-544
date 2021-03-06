package edu.miu.carRental.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.carRental.domain.Booking;
import edu.miu.carRental.domain.Car;
import edu.miu.carRental.service.BookingService;
import edu.miu.carRental.service.CarService;

@RestController
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private BookingService bookingService;

	@GetMapping("car/check_availiable")
	public List<Car> getAvailableCars(@RequestParam String start, String end) {
		List<Car> cars = carService.findAll().stream().filter(car -> car.getCarStatus().equals("available"))
				.collect(Collectors.toList());
		LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
		LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
		List<Booking> bookings = bookingService.getBookingsWithinRange(startDate, endDate);
		if (!bookings.isEmpty()) {
			List<Long> carIds = new ArrayList<>();
			for (Booking b : bookings) {
				carIds.add(b.getCar().getCarId());
			}
			cars = cars.stream().filter(car -> !carIds.contains(car.getCarId())).collect(Collectors.toList());
		}
		return cars;
	}

	@GetMapping("car/get_all")
	public List<Car> getAllCars() {
		return carService.findAll();
	}

	@GetMapping("car/get/{id}")
	public Car getCar(@PathVariable Long id) {
		Car car = carService.findById(id);
		return car;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("car/add")
	public Car addCar(@Valid @RequestBody Car car) {
		return carService.save(car);
	}

	@PutMapping("car/update")
	public Car updateCar(@Valid @RequestBody Car car) {
		return carService.save(car);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "car/delete/{id}")
	public void deleteCar(@PathVariable Long id) {
		carService.delete(id);
	}

}
