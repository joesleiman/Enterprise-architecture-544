package edu.miu.carRental.controller;

import java.util.List;
import java.util.Random;

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

import edu.miu.carRental.domain.Booking;
import edu.miu.carRental.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("booking/add_public")
	public Booking addBookingPublic(@Valid @RequestBody Booking booking) {
		booking.setReferenceNumber(getSaltString());
		return bookingService.save(booking);
	}

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("booking/search/{referenceNumber}")
	public List<Booking> searchBooking(@PathVariable String referenceNumber) {
		return bookingService.searchBookings(referenceNumber);
	}

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@PutMapping("booking/cancel/{referenceNumber}")
	public Booking cancelBooking(@PathVariable String referenceNumber) {
		return bookingService.customerCancelBooking(referenceNumber, "Canceled");
	}

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("booking/get_reference_number/{referenceNumber}")
	public Booking getBooking(@PathVariable String referenceNumber) {
		return bookingService.findByReferenceNumber(referenceNumber);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("booking/get_all")
	public List<Booking> getAllBookings() {
		return bookingService.findAll();
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("booking/get_id/{id}")
	public Booking getBooking(@PathVariable Long id) {
		return bookingService.findById(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("booking/add")
	public Booking addBooking(@Valid @RequestBody Booking booking) {
		booking.setReferenceNumber(getSaltString());
		return bookingService.save(booking);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping("booking/update")
	public Booking updateBooking(@Valid @RequestBody Booking booking) {
		return bookingService.save(booking);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@DeleteMapping(value = "booking/delete/{id}")
	public void deleteBooking(@PathVariable Long id) {
		bookingService.delete(id);
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PutMapping("booking/change_status")
	public Booking changeBookingStatus(@RequestBody Booking booking) {
		return bookingService.changeBookingStatus(booking.getReferenceNumber(), booking.getBookingStatus());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("bookings/search_string/{searchString}")
	public List<Booking> searchBookings(@PathVariable String searchString) {
		return bookingService.searchBookings(searchString);
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 8) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

}
