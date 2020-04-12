package edu.miu.carRental.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="bookings")
public class Booking implements Serializable{
	
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@Column(name = "reference_number", nullable = false, unique = true)
	@NotNull(message = "*Please provide reference number")
	private String referenceNumber;
	
	@Column(name = "booking_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "*Please provide booking date")
	private LocalDate bookingDate;

	@Column(name = "start_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "*Please provide booking start date")
    private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "*Please provide booking end date")
    private LocalDate endDate;
	
	@Column(name = "total_price", nullable = false)
	@NotNull(message = "*Please provide total price")
	private Double totalPrice;
	
	@Column(name = "booking_status", nullable = false)
	@NotNull(message = "*Please provide booking status")
	private String bookingStatus;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;
	
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@JoinColumn(name="payment_id", nullable = false, unique = true)
	private Payment payment;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="customer_id", nullable = false)
	private Customer customer;

    public Booking() {
    	
    }
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public LocalDate getBookingDate() {
		return this.bookingDate;
		//return Date.valueOf(bookingDate).toLocalDate().plusDays(1);
	}

	public void setBookingDate(LocalDate bookingDate) {
		//this.bookingDate = LocalDate.now();
		this.bookingDate = bookingDate;
		//this.bookingDate = Date.valueOf(bookingDate).toLocalDate();
	}

	public LocalDate getStartDate() {
		return startDate;
		//return Date.valueOf(startDate).toLocalDate().plusDays(1);
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
		//this.startDate = Date.valueOf(startDate).toLocalDate().plusDays(1);
	}

	public LocalDate getEndDate() {
		return endDate;
		//return Date.valueOf(endDate).toLocalDate().plusDays(1);
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
		//this.endDate = Date.valueOf(endDate).toLocalDate().plusDays(1);
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		long duration = ChronoUnit.DAYS.between(this.startDate, this.endDate);
		this.totalPrice = duration*this.getCar().getPricePerDay();
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	

}