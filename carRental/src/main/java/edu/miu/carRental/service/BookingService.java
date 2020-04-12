package edu.miu.carRental.service;

import java.time.LocalDate;
import java.util.List;

import edu.miu.carRental.domain.Booking;

public interface BookingService {
	
	public List<Booking> findAll();
	
	public Booking findById(Long id);
	
	public Booking save(Booking booking);
	
	public void delete(Long id);
	
	public Booking changeBookingStatus(String referenceNumber, String status);
	
	public List<Booking> searchBookings(String searchString);
	
	public Booking customerCancelBooking(String referenceNumber, String status);
	
	public Booking findByReferenceNumber(String referenceNumber);
	
	public List<Booking> getBookingsWithinRange(LocalDate startDate, LocalDate endDate);
	
	public Booking updateBooking(Long id, Booking booking);
}
