package edu.miu.carRental.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.carRental.domain.Booking;
import edu.miu.carRental.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/booking")
	public Booking addBookingPublic(@Valid @RequestBody Booking booking) {
		booking.setReferenceNumber(getSaltString());
		return bookingService.save(booking);
	}

	@GetMapping("/search_booking/{referenceNumber}")
	public List<Booking> searchBooking(@PathVariable String referenceNumber) {
		return bookingService.searchBookings(referenceNumber);
	}

	@PutMapping("/cancel_booking/{referenceNumber}")
	public Booking cancelBooking(@PathVariable String referenceNumber) {
		return bookingService.customerCancelBooking(referenceNumber, "Canceled");
	}

	@GetMapping("/booking/{referenceNumber}")
	public Booking getBooking(@PathVariable String referenceNumber) {
		return bookingService.findByReferenceNumber(referenceNumber);
	}

	@GetMapping("employee/bookings")
	public List<Booking> getAllBookings() {
		return bookingService.findAll();
	}

	@GetMapping("/employee/bookings/{id}")
	public Booking getBooking(@PathVariable Long id) {
		return bookingService.findById(id);
	}

	@PostMapping("/employee/bookings")
	public Booking addBooking(@Valid @RequestBody Booking booking) {
		booking.setReferenceNumber(getSaltString());
		return bookingService.save(booking);
	}

	@PutMapping("/employee/bookings")
	public Booking updateBooking(@Valid @RequestBody Booking booking) {
		return bookingService.save(booking);
	}

	@DeleteMapping(value = "/employee/bookings/{id}")
	public void deleteBooking(@PathVariable Long id) {
		bookingService.delete(id);
	}

	@PutMapping("/employee/bookings/change_status")
	public Booking changeBookingStatus(@RequestBody Booking booking) {
		return bookingService.changeBookingStatus(booking.getReferenceNumber(), booking.getBookingStatus());
	}

	@GetMapping("/employee/bookings/search/{searchString}")
	public List<Booking> searchBookings(@PathVariable String searchString) {
		return bookingService.searchBookings(searchString);
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

}
