package edu.miu.carRental.controller;

import java.util.List;

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
	
	//@PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/customer_info")
    public Customer add(@RequestBody Customer customer){
        return customerService.save(customer);
    }
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/employee/customers")
    public List<Customer> getAllCustomer() {
        return customerService.findAll();
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("employee/customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
    	Customer customer= customerService.findById(id);
        return customer;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("admin/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PutMapping("employee/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value ="admin/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
    	customerService.delete(id);
    }
}
