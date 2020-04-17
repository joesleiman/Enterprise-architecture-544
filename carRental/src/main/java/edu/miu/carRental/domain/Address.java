package edu.miu.carRental.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;

	@Column(name = "street_line")
	@NotEmpty(message = "{Validation.required}")
	private String streetLine;

	@Column(name = "city")
	@NotEmpty(message = "{Validation.required}")
	private String city;

	@Column(name = "state")
	@NotEmpty(message = "{Validation.required}")
	private String state;

	@Column(name = "zip_code")
	@NotEmpty(message = "{Validation.required}")
	@Range(min = 1, max = 99999, message = "{Range.zipcode}")
	private String zipCode;

	@Column(name = "country")
	@NotEmpty(message = "{Validation.required}")
	private String country;

	public Address() {
	}

	public Address(String streetLine, String city, String state, String zipCode, String country) {
		this.streetLine = streetLine;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreetLine() {
		return streetLine;
	}

	public void setStreetLine(String streetLine) {
		this.streetLine = streetLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
