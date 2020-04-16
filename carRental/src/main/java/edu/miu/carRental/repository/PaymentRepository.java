package edu.miu.carRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.carRental.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
