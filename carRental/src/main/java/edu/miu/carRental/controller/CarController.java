package edu.miu.carRental.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(allowedHeaders = "*")
public class CarController {

    @Autowired
    private CarService carService;
    
    @Autowired
	private BookingService bookingService;
    
    @GetMapping("/cars")
    public List<Car> getAllCarsPublic() {
        return carService.findAll();
    }    
    
    @GetMapping("/cars/{id}")
    public Car getCarPublic(@PathVariable Long id){
        Car car= carService.findById(id);
        return car;
    }
    
    @GetMapping("/check_availiable_cars")
  	public List<Car> getAvailableCars(@RequestParam String start, String end){
  		List<Car> cars = carService.findAll().stream()
  				.filter(car->car.getCarStatus().equals("available")).collect(Collectors.toList());
  		LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_DATE);
  		LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
  		List<Booking> bookings = bookingService.getBookingsWithinRange(startDate, endDate);  		
  		if(!bookings.isEmpty()) {
  			List<Long> carIds = new ArrayList<>();
  			for(Booking b: bookings){
  				carIds.add(b.getCar().getCarId());
  			}
  			cars = cars.stream().filter(car->!carIds.contains(car.getCarId())).collect(Collectors.toList());
  		}  
  		
  		return cars;
  	}
    
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("employee/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("employee/cars/{id}")
    public Car getCar(@PathVariable Long id){
        Car car= carService.findById(id);
        return car;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("admin/cars")
    public Car addCar(@Valid @RequestBody Car car){
        return carService.save(car);
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PutMapping("employee/cars")
    public Car updateCar(@Valid @RequestBody Car car){
        return carService.save(car);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value ="admin/cars/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.delete(id);
    }
}

