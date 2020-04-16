package edu.miu.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.carRental.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
