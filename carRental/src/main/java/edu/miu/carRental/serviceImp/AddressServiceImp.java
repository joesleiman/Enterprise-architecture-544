package edu.miu.carRental.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.Address;
import edu.miu.carRental.exceptions.RecordNotFoundException;
import edu.miu.carRental.repository.AddressRepository;
import edu.miu.carRental.service.AddressService;

@Service
public class AddressServiceImp implements AddressService{
		
	private AddressRepository addressRepository;
	
	@Autowired
	public AddressServiceImp(AddressRepository ar) {
		this.addressRepository = ar;
	}

	@Override
	public List<Address> findAll() {
		// TODO Auto-generated method stub
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

	@Override
	public Address findById(Long id) {
		// TODO Auto-generated method stub
		return addressRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Address with id : " + id+" is not available"));
	}

	@Override
	public void delete(Long id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Address with id : " + id+" is not available"));		
		addressRepository.delete(address);
		
	}

}
