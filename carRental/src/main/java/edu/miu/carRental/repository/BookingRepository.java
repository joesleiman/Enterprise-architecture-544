package edu.miu.carRental.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.miu.carRental.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{	
	
	public List<Booking> findAllByReferenceNumber(String referenceNumber);
	
	public List<Booking> findAllByTotalPrice(Double totalPrice);
	
	public Optional<Booking> findByReferenceNumber(String referenceNumber);
	
	//public List<Booking> findAllByBookingDateContainingOrStartDateContainingOrEndDate(LocalDate bookingDate, LocalDate startDate, LocalDate endDate);
	
	public List<Booking> findAllByBookingDateOrStartDateOrEndDate(LocalDate bookingDate, LocalDate startDate, LocalDate endDate);
	
	public List<Booking> findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

}
