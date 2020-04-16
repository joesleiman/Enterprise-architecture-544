package edu.miu.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.carRental.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
