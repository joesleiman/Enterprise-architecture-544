package edu.miu.carRental.service;

import java.util.List;

import edu.miu.carRental.domain.Car;

public interface CarService {

	public List<Car> findAll();

	public Car findById(Long id);

	public Car save(Car car);

	public void delete(Long id);

	public abstract Car updateCar(Long Id, Car car);

}
