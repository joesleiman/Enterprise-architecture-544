package edu.miu.carRental.service;

import java.util.List;

import edu.miu.carRental.domain.Payment;

public interface PaymentService {
	
	public List<Payment> findAll();	
	public Payment save(Payment payment);	
	public Payment findById(Long id);
	public void delete(Long id);
}
