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

import edu.miu.carRental.domain.Payment;
import edu.miu.carRental.service.PaymentService;

@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("payment/get_all")
	public List<Payment> getAllPayments() {
		return paymentService.findAll();
	}

	@GetMapping("payment/get/{id}")
	public Payment getPayment(@PathVariable Long id) {
		Payment payment = paymentService.findById(id);
		return payment;
	}

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@PostMapping("payment/add")
	public Payment add(@Valid @RequestBody Payment payment) {
		return paymentService.save(payment);
	}

	@PutMapping("payment/update")
	public Payment updatePayment(@Valid @RequestBody Payment payment) {
		return paymentService.save(payment);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping(value = "payment/delete/{id}")
	public void deletePayment(@PathVariable Long id) {
		paymentService.delete(id);
	}

}
