package edu.miu.carRental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/customer_info")
	public Customer add(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@GetMapping("/employee/customers")
	public List<Customer> getAllCustomer() {
		return customerService.findAll();
	}

	@GetMapping("employee/customers/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		Customer customer = customerService.findById(id);
		return customer;
	}

	@PostMapping("admin/customers")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PutMapping("employee/customers")
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@DeleteMapping(value = "admin/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.delete(id);
	}

}
