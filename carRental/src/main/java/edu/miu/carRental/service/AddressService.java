package edu.miu.carRental.service;

import java.util.List;

import edu.miu.carRental.domain.Address;

public interface AddressService {
	
	public List<Address> findAll();
	
	public Address save(Address address);
	
	public Address findById(Long id);
	
	public void delete(Long id);
	
	

}
