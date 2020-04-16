package edu.miu.carRental.service;

import java.util.List;

import edu.miu.carRental.domain.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer save(Customer customer);

	public Customer findById(Long id);

	public void delete(Long id);

}
