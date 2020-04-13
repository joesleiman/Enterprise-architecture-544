package edu.miu.carRental.serviceImp;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.miu.carRental.domain.Booking;
import edu.miu.carRental.exception.RecordNotFoundException;
import edu.miu.carRental.repository.BookingRepository;
import edu.miu.carRental.service.BookingService;

@Service
public class BookingServiceImp implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking findById(Long id) {
		//return bookingRepository.findById(id).orElse(null);
		return bookingRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Booking with id : " + id+" is not available"));
	}

	@Override
	public Booking save(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public void delete(Long id) {
		Booking booking = bookingRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Booking with id : " + id+" is not available"));
		
		bookingRepository.delete(booking);
	}

	@Override
	public Booking changeBookingStatus(String referenceNumber, String status) {
		Booking booking = bookingRepository.findByReferenceNumber(referenceNumber)
				.orElseThrow(() -> new RecordNotFoundException("Booking with reference number: " + referenceNumber + " is not available."));
		booking.setBookingStatus(status);
		
		return bookingRepository.save(booking);
	}

	@Override
	public List<Booking> searchBookings(String searchString) {
		if(containDecimalPoint(searchString) && isMoney(searchString)) {
			return bookingRepository.findAllByTotalPrice(Double.parseDouble(searchString));
		}else if(isDate(searchString)) {
			LocalDate searchDate = LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE);
			//return bookingRepository.findAllByBookingDateContainingOrStartDateContainingOrEndDate(searchDate, searchDate, searchDate);
			return bookingRepository.findAllByBookingDateOrStartDateOrEndDate(searchDate, searchDate, searchDate);
		}else {
			return bookingRepository.findAllByReferenceNumber(searchString);
		}
	}
	
	private boolean isMoney(String searchString) {
		boolean isParseableAsMoney = false;
		try {
			Double.parseDouble(searchString);
			isParseableAsMoney = true;
		} catch (Exception e) {
			if(e instanceof ParseException) {
				isParseableAsMoney =false;
			}
		}
		return isParseableAsMoney;
	}
	
	private boolean isDate(String searchString) {
		boolean isParseableAsDate = false;
		try {
			LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE);
			isParseableAsDate = true;
		} catch (Exception e) {
			if(e instanceof ParseException) {
				isParseableAsDate = false;
			}
		}
		return isParseableAsDate;
	}
	
	private boolean containDecimalPoint(String searchString) {
		return searchString.contains(".");
	}

	@Override
	public Booking customerCancelBooking(String referenceNumber, String status) {
		Booking booking = bookingRepository.findByReferenceNumber(referenceNumber)
				.orElseThrow(() -> new RecordNotFoundException("Booking with reference number: " + referenceNumber + " is not available."));
		booking.setBookingStatus(status);
		
		return bookingRepository.save(booking);
	}

	@Override
	public Booking findByReferenceNumber(String referenceNumber) {
		return bookingRepository.findByReferenceNumber(referenceNumber).orElse(null);
	}

	@Override
	public List<Booking> getBookingsWithinRange(LocalDate startDate, LocalDate endDate) {
		LocalDate searchStartDate = LocalDate.parse(startDate.toString(), DateTimeFormatter.ISO_DATE);
		LocalDate searchEndDate = LocalDate.parse(endDate.toString(), DateTimeFormatter.ISO_DATE);
		return bookingRepository.findAllByStartDateGreaterThanEqualAndEndDateLessThanEqual(searchStartDate, searchEndDate);
				
	}

	@Override
	public Booking updateBooking(Long id, Booking booking) {
		return bookingRepository.findById(id)
                .map(b -> {
                	b.setBookingId(id);
                	b.setReferenceNumber(booking.getReferenceNumber());
                	b.setBookingDate(booking.getBookingDate());
                	b.setStartDate(booking.getStartDate());
                	b.setEndDate(booking.getEndDate());
                	b.setBookingStatus(booking.getBookingStatus());
                	b.setTotalPrice(booking.getTotalPrice());
                	b.setCustomer(booking.getCustomer());
                	b.setCar(booking.getCar());
                	b.setPayment(booking.getPayment());
                return bookingRepository.save(b);
               }).orElseGet(() -> {
                    return bookingRepository.save(booking);
                });
	}
}
