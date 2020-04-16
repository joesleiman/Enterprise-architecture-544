package edu.miu.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.carRental.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
