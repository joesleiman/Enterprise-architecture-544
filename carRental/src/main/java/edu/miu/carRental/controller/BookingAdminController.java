package edu.miu.carRental.controller;

import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.miu.carRental.domain.Booking;
import edu.miu.carRental.service.BookingService;

@Controller
public class BookingAdminController {

	@Autowired
	private BookingService bookingService;
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@GetMapping("employee/nbookings")
	public ModelAndView getAllBookings(){
		ModelAndView modelAndView = new ModelAndView();
        List<Booking> bookings = bookingService.findAll();
        modelAndView.addObject("bookings",bookings);
        modelAndView.setViewName("booking/list");
        return modelAndView;
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@PostMapping(value = {"/employee/nbookings/edit"})
	public String edit(@Valid @ModelAttribute("booking") Booking booking,
			BindingResult result, Model model) {	
		
		if(result.hasErrors()) {			
			model.addAttribute("errors", result.getAllErrors());
			
			return "booking/edit";
		}
	
		booking = bookingService.updateBooking(booking.getBookingId(), booking);
		
		return "redirect:employee/nbookings";
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@GetMapping("/employee/nbookings/edit/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("booking", bookingService.findById(id));
		
		return "booking/edit";
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@GetMapping("/employee/nbookings/change_status/{bookingId}")
	public String changeBookingStatus(@PathVariable Long bookingId, Model model) {
		Booking b = bookingService.findById(bookingId);
		bookingService.changeBookingStatus(b.getReferenceNumber(), "Canceled");
		return "redirect:/employee/nbookings";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = {"/employee/nbookings/delete/{bookingId}"})
	public String deleteBooking(@PathVariable Long bookingId, Model model) {
		bookingService.delete(bookingId);
        return "redirect:/employee/nbookings";
    }
	
	/*
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@PutMapping("/employee/nbookings")
	public Booking updateBooking(@RequestBody Booking booking) {
		return bookingService.save(booking);
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@DeleteMapping(value="/employee/nbookings/{id}")
	public void deleteBooking(@PathVariable Long id) {
		bookingService.delete(id);
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@PutMapping("/employee/nbookings/change_status")
	public Booking changeBookingStatus(@RequestBody Booking booking) {
		return bookingService.changeBookingStatus(booking.getReferenceNumber(), booking.getBookingStatus());
	}
	
	@PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
	@GetMapping("/employee/nbookings/search/{searchString}")
	public List<Booking> searchBookings(@PathVariable String searchString){
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

    }*/
}
