package edu.miu.carRental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.carRental.domain.Customer;
import edu.miu.carRental.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("customer/get_all")
	public List<Customer> getAllCustomer() {
		return customerService.findAll();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("customer/get/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		return customer;
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("customer/add")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping("customer/update")
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "customer/delete/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.delete(id);
	}

}
