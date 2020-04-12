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

import edu.miu.carRental.domain.Payment;
import edu.miu.carRental.service.PaymentService;


@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/payment")
    public Payment add(@RequestBody Payment payment){
        return paymentService.save(payment);
    }
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/employee/payments")
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/employee/payments/{id}")
    public Payment getPayment(@PathVariable Long id){
    	Payment payment= paymentService.findById(id);
        return payment;
    }
   
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PutMapping("/employee/payments")
    public Payment updatePayment(@RequestBody Payment payment){
        return paymentService.save(payment);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value ="/admin/payments/{id}")
    public void deletePayment(@PathVariable Long id){
    	paymentService.delete(id);
    }
}
