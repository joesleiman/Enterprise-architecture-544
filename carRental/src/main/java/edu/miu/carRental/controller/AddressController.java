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

import edu.miu.carRental.domain.Address;
import edu.miu.carRental.service.AddressService;

@RestController
public class AddressController {
		
	private AddressService addressService;
	
	@Autowired
	public AddressController(AddressService as) {
		this.addressService = as;
	}
	
	@PostMapping("/address")
    public Address add(@Valid @RequestBody Address address){
        return addressService.save(address);
    }
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/employee/address")
    public List<Address> getAllAddress() {
        return addressService.findAll();
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("employee/address/{id}")
    public Address getAddress(@PathVariable Long id){
    	Address address= addressService.findById(id);
        return address;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("admin/address")
    public Address addAddress(@Valid @RequestBody Address address){
        return addressService.save(address);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("employee/address")
    public Address updateAddress(@RequestBody Address address){
        return addressService.save(address);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value ="admin/address/{id}")
    public void deleteAddress(@PathVariable Long id){
    	addressService.delete(id);
    }
}
