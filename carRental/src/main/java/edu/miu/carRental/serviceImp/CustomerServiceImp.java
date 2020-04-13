package edu.miu.carRental.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.Customer;
import edu.miu.carRental.exception.RecordNotFoundException;
import edu.miu.carRental.repository.CustomerRepository;
import edu.miu.carRental.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Customer with id : " + id+" is not available"));
	}

	@Override
	public void delete(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Customer with id : " + id+" is not available"));
		customerRepository.delete(customer);
	}

}
