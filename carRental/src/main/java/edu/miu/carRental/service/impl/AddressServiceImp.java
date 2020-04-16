package edu.miu.carRental.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.Address;
import edu.miu.carRental.exception.RecordNotFoundException;
import edu.miu.carRental.repository.AddressRepository;
import edu.miu.carRental.service.AddressService;

@Service
public class AddressServiceImp implements AddressService {

	private AddressRepository addressRepository;

	@Autowired
	public AddressServiceImp(AddressRepository ar) {
		this.addressRepository = ar;
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address findById(Long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Address with id : " + id + " is not available"));
	}

	@Override
	public void delete(Long id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Address with id : " + id + " is not available"));
		addressRepository.delete(address);

	}

}
