package edu.miu.carRental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.Car;
import edu.miu.carRental.repository.CarRepository;
import edu.miu.carRental.service.CarService;

@Service
public class CarServiceImp implements CarService {

	@Autowired
	private CarRepository carRepository;

	public List<Car> findAll() {
		return carRepository.findAll();
	}

	public Car findById(Long id) {
		return carRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Car with id : " + id + " is not available"));
	}

	public Car save(Car car) {
		carRepository.save(car);
		return car;
	}

	public void delete(Long id) {
		Car car = carRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Car with id : " + id + " is not available"));
		carRepository.delete(car);
	}

	@Override
	public Car updateCar(Long Id, Car car) {
		return carRepository.findById(Id).map(carToUpdate -> {
			carToUpdate.setCarStatus(car.getCarStatus());
			carToUpdate.setCarVinNumber(car.getCarVinNumber());
			carToUpdate.setCategoryName(car.getCategoryName());
			carToUpdate.setMake(car.getMake());
			carToUpdate.setModel(car.getModel());
			carToUpdate.setPlateNumber(car.getPlateNumber());
			carToUpdate.setPricePerDay(car.getPricePerDay());
			carToUpdate.setYear(car.getYear());
			return carRepository.save(carToUpdate);
		}).orElseGet(() -> {
			return carRepository.save(car);
		});
	}

}
