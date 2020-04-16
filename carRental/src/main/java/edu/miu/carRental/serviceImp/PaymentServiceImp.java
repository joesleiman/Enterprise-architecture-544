package edu.miu.carRental.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.miu.carRental.domain.Payment;
import edu.miu.carRental.exception.RecordNotFoundException;
import edu.miu.carRental.repository.PaymentRepository;
import edu.miu.carRental.service.PaymentService;

@Service
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment findById(Long id) {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Payment with id : " + id + " is not available"));
	}

	@Override
	public void delete(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Payment with id : " + id + " is not available"));
		paymentRepository.delete(payment);
	}

}
