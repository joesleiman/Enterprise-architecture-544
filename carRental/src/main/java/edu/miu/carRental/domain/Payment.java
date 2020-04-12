package edu.miu.carRental.domain;

import java.time.LocalDate;
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
@Table(name = "payments")
public class Payment {
	
	@Id
	@Column(name = "payment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	@Column(name = "payment_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "*Please provide payment date") 
	private LocalDate paymentDate;

	@Column(name = "card_number")
	@NotNull(message = "*Please provide payment card number") 
    private Long cardNumber;

	@Column(name = "card_cvv")
	@NotNull(message = "*Please provide payment card CVV") 
    private Integer cardCVV;
    
	@Column(name = "total_price") 
    private Double totalPrice;
    
	@Column(name = "payment_status")
	@NotNull(message = "*Please provide payment payment status")  
	private String paymentStatus;
	
	@ManyToOne
	//@Column(name="address_id", nullable = false)
	@NotNull(message = "*Please provide billingAddress") 
    private Address billingAddress;
	
	public Payment() {
		
	}
	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getCardCVV() {
		return cardCVV;
	}

	public void setCardCVV(Integer cardCVV) {
		this.cardCVV = cardCVV;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	
	
}